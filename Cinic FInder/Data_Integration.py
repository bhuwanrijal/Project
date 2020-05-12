import csv
import pandas as pd
import petl as etl
import os

#################################Task 1.1 data cleansing task###########################

# method to validate the phone number it verifies either the phone number is in right format or not
def phone_validate(ph,state) :
    
    state_val = {'NSW' : '2' ,'VIC' : '3','QLD' : '7' , 'SA' : '9'}
    state_val_list = ['2','02','3','03','7','07','9','09']
    
    #step 1 merging into single string without space and '()'
    ph = ph.replace(" ","")
    ph = ph.replace("(","")
    ph = ph.replace(")","")
    
    
    #step 2 fetch last and first 4 letters of phone number
    ph_last = ph[-4:]  # fetch last 4 characters
    ph = ph[:-4]       # remove last four characters from phone number string

    ph_first = ph[-4:] # fetch first 4 characters 
    ph = ph[:-4]       # remove first four characters from phone number string
    
    #step 3 fetch regional code
    ph = ph.replace("0","") #remove zero
    rCode = state_val[state]
    
    st = '+61' + ' ' + rCode + ' ' + ph_first + ' ' + ph_last;
    return st;

#open offices.csv file and update the phone number in write format
df = pd.read_csv("offices.csv") 
for index,row in df.iterrows() :
    number = phone_validate(row['Phone Number'],row['State'])    
    df.set_value(index,'Phone Number',number)

df.to_csv('offices_mod.csv') 


os.rename("offices.csv","offices_old.csv")
os.rename("offices_mod.csv","offices.csv")
os.remove("offices_old.csv")









################ Task 1.2 merging data from different sources and create single CSV file###########################
var_Office_Services = etl.fromcsv('Office_Services.csv')

var_Services = etl.fromxml('Services.xml', 'service', {"ServiceID":"ServiceID", "ServiceName":"ServiceName"})

var_Offices = etl.fromcsv("offices.csv");

var_Office_Locations = etl.fromxml('Office_Locations.xml', 'office', {"officeID":"officeID", "Lat":"Lat","Lon":"Lon"});


etl.tocsv(var_Services,"var_Services.csv");


var_merge_Services_Ofc_Services = etl.join(var_Services,var_Office_Services,key = "ServiceID");

var_merge_Services_Ofc_Services_Offices = etl.join(var_merge_Services_Ofc_Services,var_Offices,key = "OfficeID");

etl.tocsv(var_merge_Services_Ofc_Services_Offices,'var_merge_Services_Ofc_Services_Offices.csv');

var_merge_Office_Services = etl.join(var_Office_Services,var_Services,key = "ServiceID");

Office_Service_Locations = etl.join(var_merge_Services_Ofc_Services_Offices,var_Office_Locations,lkey = 'OfficeID',rkey = "officeID");




def change_key(self, old, new):
    for _ in range(len(self)):
        k, v = self.popitem(False)
        self[new if old == k else k] = v


d = Office_Service_Locations.columns();


change_key(d,'Contact Name','Office');
change_key(d,'Phone Number','Phone');
change_key(d,'ServiceName','Service');
Office_Service_Locations = etl.setheader(Office_Service_Locations, d);

Office_Service_Locations = etl.cutout(Office_Service_Locations,"State")
Office_Service_Locations = etl.cutout(Office_Service_Locations,"Postcode")
#Office_Service_Locations = etl.cutout(Office_Service_Locations,"ServiceID")

etl.tocsv(Office_Service_Locations,'Office_Service_Locations.csv');