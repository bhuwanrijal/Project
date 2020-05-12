import csv
import pandas as pd
import os

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




df = pd.read_csv("offices.csv") 
for index,row in df.iterrows() :
    number = phone_validate(row['Phone Number'],row['State'])    
    df.set_value(index,'Phone Number',number)

df.to_csv('offices_mod.csv') 


os.rename("offices.csv","offices_old.csv")
os.rename("offices_mod.csv","offices.csv")
os.remove("offices_old.csv")