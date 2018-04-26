from flask import Flask
from flask import request
import random
import mysql.connector
from mysql.connector import Error
from Model import Model;


app = Flask(__name__)

shot = random.randint(2, 1585)
model = None;

@app.route('/start', methods = ['POST'])
def start():
	global model;
	playerId = request.args.get("player");
	model = Model(id);
	print("New Model Made...");
	return "true";

# takes in argument mode, ex. http://locahost:8080/nextShot?mode=#######
@app.route('/nextShot', methods = ['POST'])
def getNextShot():
	global model;
	mode = request.args.get("mode");
	conn = mysql.connector.connect(host='localhost',
								database='smartserve',
								user='root',
								password='smarTserve91',
								port=3306);
	if(mode == "RANDOM"):
		shotId = random.choice(model.shots.keys);
	elif(mode == "SINGLEZONE"):
		shotId = mode = request.args.get("shot");
	else:
		shotId = model.shots.getNext();

	if conn.is_connected():
		print('Connected to MySQL database');
		values = callProc(conn, shotId);
		outputString = 'X='+str(values[1])+',Y='+str(values[2])+',P='+str(values[4])+',R='+str(values[3])+',I='+str(shotId)
	print(outputString)

	conn.close()
	return outputString

# accessible via http://localhost/update?id=4&returned=1
@app.route('/update', methods = ['POST'])
def update():
	global model;
	shotId = int(request.args.get("id"));
	returned = int(request.args.get("returned"));
	shotId = model.shots.update(shotId, returned);
	return "updated";

def callProc(conn, id):
	cur = conn.cursor()

	#Calling proc
	args = [id,0,0,0,0]
	result_args = cur.callproc('next_shot',args)
	return result_args

	#Runnning SQL script
	#cur.execute( "SELECT user_name, password FROM user" )
	#for user_name, password in cur.fetchall() :
	#	return (user_name + password)

app.run(host="localhost", port=8080)
