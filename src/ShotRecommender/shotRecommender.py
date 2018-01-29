from flask import Flask
import random
import mysql.connector
from mysql.connector import Error
 

app = Flask(__name__)

shot = random.randint(2, 1585)

@app.route('/')
def helloWorld():
	return 'Hello, World!'

@app.route('/nextShot', methods = ['POST'])
def getNextShot():
	conn = mysql.connector.connect(host='localhost',
								database='smartserve',
								user='root',
								password='smarTserve91',
								port=3306)
	if conn.is_connected():
		print('Connected to MySQL database')
		values = callProc(conn)
		#outputString = 'X='+str(values[0])+',Y='+str(values[0])+',V='+str(values[0])+',W='+str(values[0])
		outputString = 'value: ' + values
	print(outputString)
	conn.close()
	return outputString


def callProc(conn):
	cur = conn.cursor()

	#Calling proc
	args = [4]
	result_args = cur.callproc('next_shot',args)
	return str(result_args[0])

	#Runnning SQL script
	#cur.execute( "SELECT user_name, password FROM user" )
	#for user_name, password in cur.fetchall() :
	#	return (user_name + password)

	
  

app.run(host="localhost", port=8080)
