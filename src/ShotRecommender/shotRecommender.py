from flask import Flask
from flask import request
import random
import mysql.connector
from mysql.connector import Error
 

app = Flask(__name__)

shot = random.randint(2, 1585)

@app.route('/')
def helloWorld():
	return 'Hello, World!'

# takes in argument mode, ex. http://locahost:8080/nextShot?mode=#######
@app.route('/nextShot', methods = ['POST'])
def getNextShot():
	mode = request.args.get("mode");
	conn = mysql.connector.connect(host='localhost',
								database='smartserve',
								user='root',
								password='smarTserve91',
								port=3306)
	if conn.is_connected():
		print('Connected to MySQL database')
		values = callProc(conn)
		outputString = 'X='+str(values[1])+',Y='+str(values[2])+',V='+str(values[3])+',W='+str(values[4])
	print(outputString)

	conn.close()
	return outputString


def callProc(conn):
	cur = conn.cursor()

	#Calling proc
	args = [4,0,0,0,0]
	result_args = cur.callproc('next_shot',args)
	return result_args

	#Runnning SQL script
	#cur.execute( "SELECT user_name, password FROM user" )
	#for user_name, password in cur.fetchall() :
	#	return (user_name + password)

	
  

app.run(host="localhost", port=8080)
