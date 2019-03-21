import requests, json, math, sys

class Plane:
	def __init__(self,dist,callsign,lat,long,alt,country,ICAO):
		self.dist = dist
		self.callsign = callsign
		self.lat = lat
		self.long = long
		self.alt = alt
		self.country = country
		self.ICAO = ICAO
	
def main(loc,lat, long):
	planes = []
	r = requests.get('https://opensky-network.org/api/states/all')
	planes = json.loads(r.text)
	planes_list = planes['states']
	
	output = []
	for plane in planes_list:
		curplane = Plane(sys.float_info.max,plane[1],plane[6],plane[5],plane[7],plane[2],plane[0])
		if(curplane.lat != None and curplane.lat != None):
			dist = math.sqrt( math.pow((float(lat) - float(curplane.lat)), 2) + math.pow((float(long) - float(curplane.long)),2) )
			curplane.dist = dist
			
		output.append(curplane)	
	
	p = sorted(output, key=lambda plane: plane.dist)[0]
	
	print("The closest airborne plane to ",loc,lat,long," is ")
	print("\t", p.dist, p.callsign, p.lat, p.long, p.alt, p.country, p.ICAO)
		
if __name__ == "__main__":
	main('John F. Kennedy Airport', 40.6413, 73.7781)
	main('Eifel Tower', 48.8584, 2.2945)
