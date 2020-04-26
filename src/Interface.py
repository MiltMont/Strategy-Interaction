from __future__ import print_function, unicode_literals
import regex

from pprint import pprint
from PyInquirer import style_from_dict, Token, prompt
from PyInquirer import Validator, ValidationError

#Number validator
class NumberValidator(Validator):
	def validate(self, document):
		try:
			int(document.text)
		except ValueError:
			raise ValidationError(
				message='Please enter a number') 
			
#Number validator for NumOfPlayers
class playsValidator(Validator):
	def validate(self, document):
		try:
			int(document.text)
			if (int(document.text)<2):	
				raise ValidationError(message='Please enter a number bigger than 1')
		except ValueError:
			raise ValidationError(
				message='Please enter a number') #ADD CURSOR TO END

#Number validator for NumOfRounds
class roundsValidator(Validator):
	def validate(self, document):
		try:
			int(document.text)
			if (int(document.text)<1):	
				raise ValidationError(message='Please enter a number bigger than 0')
		except ValueError:
			raise ValidationError(
				message='Please enter a number') #ADD CURSOR TO END

class CLI:

	#Attributes
	style = style_from_dict({
		Token.Separator: '#cc5454',
		Token.QuestionMark: '#cc5454',
		Token.Selecterd: '#cc5454',
		Token.Pointer: '#cc5454',
		Token.Instruction: '',
		Token.Answer: '#cc5454',
		Token.Question: '',	
		})

	NameOfPlayers = []
	StrategyOfPlayers = []

	def __init__(self):
		self.SelectedPreset = 'default'

	#Setting the first stage.
	def setFirst(self):
		self.first = [
			{
			'type': 'input',
			'name': 'NumOfPlayers',
			'message': 'Enter the number of players',
			'validate': playsValidator,
			'filter': lambda val: int(val)
			},
			{
			'type': 'input',
			'name': 'InitialScores',
			'message': 'Enter the initial scores',
			'validate': NumberValidator,
			'filter': lambda val: int(val)
			},
			{#If this is true, it will display the presets
			'type': 'confirm',
			'message': 'Do you want to change the payoffs?',
			'name': 'Presets',
			'default': 'False'
			}
		]

	def promptFirst(self):
		self.setFirst()
		first = prompt(self.first, style=self.style)
		self.NumOfPlayers = first['NumOfPlayers']
		self.InitialScores = first['InitialScores']
		self.Presets = first['Presets']

	def getNumOfPlayers(self):
		return self.NumOfPlayers

	def getInitialScores(self):
		return self.InitialScores

	def getPresets(self):
		return self.Presets

	def setSecond(self):
		self.second = [
			{#Displays action matrix presets.
				'type': 'list',
				'name': 'ActionMatrixPresets',
				'message': 'Please select the preset',
				'choices': ['Cooperative', 'Prissioner', 'Unbalanced'],
				'filter': lambda val: val.lower()
			}
		]

	def promptSecond(self):
		self.setSecond()
		second = prompt(self.second, style=self.style)
		self.SelectedPreset = second['ActionMatrixPresets']

	def getSelectedPreset(self):
		return self.SelectedPreset 

	def setThird(self):
		self.third = [
			{
				'type': 'input',
				'name': 'NumOfRounds',
				'message': 'Enter the number of rounds',
				'validate': roundsValidator,
				'filter': lambda val: int(val)
			}
		]

	def promptThird(self):
		self.setThird()
		third = prompt(self.third, style=self.style)
		self.NumOfRounds = third['NumOfRounds']

	def getNumOfRounds(self):
		return self.NumOfRounds

	def promptFinal(self):
		for i in range(0, self.NumOfPlayers):
			self.final = [
				{
					'type': 'input',
					'name': 'NameOfPlayers',
					'message': 'Please enter the name of player {}'.format(i+1)
				},
				{

					'type': 'rawlist',
					'name': 'StrategyOfPlayers',
					'message': 'Please select the strategy of player {}'.format(i+1),
					'choices': [
						'SC',
						'SD',
						'TD',
						'GD',
						'DE'
						]
				}
			]

			fin = prompt(self.final, style=self.style)

			self.name = fin['NameOfPlayers']
			self.strategy = fin['StrategyOfPlayers']
			self.NameOfPlayers.append(self.name)
			self.StrategyOfPlayers.append(self.strategy)

	
	def getNamesOfPlayers(self):
		return self.NameOfPlayers

	def getStrategyOfPlayers(self):
		return self.StrategyOfPlayers

	def runAll(self):
		self.promptFirst()
		if(self.Presets == True):
			self.promptSecond()
		self.promptThird()
		self.promptFinal()
