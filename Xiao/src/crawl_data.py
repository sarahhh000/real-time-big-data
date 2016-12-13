from google.transit import gtfs_realtime_pb2
import urllib
import time
import os

i = 100
while i > 0:
    i = i - 1
    dir_path = time.strftime("%Y%m%d%H")
    if not os.path.exists(dir_path):
        os.makedirs(dir_path, 0777)

    feed = gtfs_realtime_pb2.FeedMessage()
    response = urllib.urlopen('https://transitdata.phoenix.gov/api/vehiclepositions?format=gtfs.proto')
    feed.ParseFromString(response.read())
    file_name = time.strftime("%Y%m%d%H%M%S")
    file_path = os.path.join(dir_path, file_name)
    f = open(file_path, 'w')
    for entity in feed.entity:
        f.write(str(entity.vehicle.position.latitude))
        f.write(",")
        f.write(str(entity.vehicle.position.longitude))
        f.write(",")
        f.write(str(entity.vehicle.position.speed))
        f.write(",")
        f.write(str(entity.vehicle.timestamp))
        f.write(",")
        f.write(str(entity.vehicle.vehicle.id))
        f.write("\n")
    f.close()
    time.sleep(10)