from flask import Flask
import random

app = Flask(__name__)

X = [0.191,0.572,0.953,1.334]
Y = [0.171,0.514,0.856,1.199]
#V, W are subject to change
V = [60, 65, 70, 75, 80]
W = [-5, -10, -15, 5, 10, 15]

@app.route('/')
def helloWorld():
	return 'Hello, World!'

@app.route('/nextShot', methods = ['POST'])
def getNextShot():
	outputString = 'X='+str(random.choice(X))+',Y='+str(random.choice(Y))+',V='+str(random.choice(V))+',W='+str(random.choice(W))
	print outputString
	return outputString

app.run(host="localhost", port=8080)