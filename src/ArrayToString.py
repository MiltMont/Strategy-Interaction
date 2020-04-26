class Converter:
	def ToString(self, s):

		self.str1=""

		for el in s:
			self.str1 += el
			self.str1 += ','

		return self.str1
