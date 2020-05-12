import petl as etl

################ Task 1.2 merging data from different sources and create single CSV file
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


