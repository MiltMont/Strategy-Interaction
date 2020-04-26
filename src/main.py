from Interface import CLI
from ArrayToString import Converter
from Client import Client
import os
import multiprocessing
import time

cli = CLI()
convert = Converter()
client = Client()

def runServer():
	os.system("java Run")

def runClient():
	time.sleep(1)
	client.connect()


p1 = multiprocessing.Process(target=runServer)
p2 = multiprocessing.Process(target=runClient)

#Prompting CLI
cli.runAll()

#getting data
numOfPlayers = cli.getNumOfPlayers()
initialScores = cli.getInitialScores()
preset = cli.getPresets()
selectedPreset = cli.getSelectedPreset()
numOfRounds = cli.getNumOfRounds()
namesOfPlayers = convert.ToString(cli.getNamesOfPlayers())
playersStrategies = convert.ToString(cli.getStrategyOfPlayers())


# sending data
p1.start()
runClient()



client.send(numOfPlayers)
client.send(initialScores)
client.send(selectedPreset)
client.send(numOfRounds)
client.send(namesOfPlayers)
client.send(playersStrategies)


#print('Connection ended')
