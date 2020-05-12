import petl as etl
from bottle import request, response,route,run
from bottle import post, get, put, delete
import json

@get('/getservices')
def get_services_handlder():
    services = etl.fromcsv("services.csv")
    response.headers['Access-Control-Allow-Origin'] = '*'
    response.headers['Content-type'] = 'application/json'
    etl.tojson(services,'services.json',sort_keys=False)
    return open('services.json').read()	

@get('/getoffices')
def get_offices():
    serviceId = request.query.serviceId
    print("Receive the request for office: " + serviceId)
    office_locations = etl.fromcsv("Office_Service_Locations.csv")
    response.headers['Access-Control-Allow-Origin'] = '*'
    response.headers['Content-type'] = 'application/json'
	
    table1 = etl.select(office_locations, "{ServiceID} == '" + str(serviceId) + "'")
    print(table1)
    if etl.nrows(table1) == 0 :
        defaultServiceId = "0"
        table1 = office_locations
    etl.tojson(table1,'abc.json',sort_keys=True)
    return open('abc.json').read()
	




if __name__ == '__main__':
    run(debug = True,reloader=True)	