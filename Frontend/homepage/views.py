from django.shortcuts import render
from . import process_search
import json


def topic_model(request):
	# amruta : Todo .. pass this to the front end.
	'''
	   var topic_json = {"t1":[{"text":"donald", weight:10}, {"text":"president", weight:20}], "t2":[{"text":"hi", weight:5}], "t3" : {"text":"hiiiiiii", weight:15}};
	'''
	pass

def index(request):
	my_json = [{"coords": [-63.2425206, -32.4079042],"frequency": 9},{"coords": [12.57994249, 55.68087366],"frequency": 3}];
	#my_json  = {"test":123};
	#js_data = json.dumps(my_json)		
	return render(request, 'homepage/header.html', {'my_json': my_json})


def home(request):
	if request.method == 'GET':
		# try:
		query = request.GET['search']
		# 	if query:
		# 		try:
		# 			sentiment = process_search.get_sentiment(query)
		# 			mode = "Fetched from cassandra"
		# 		except:
		# 			#process_search.connect_kafka(query)
		# 			sentiment = process_search.get_sentiment(query)
		# 			mode = "Fetched from kafka"
		# 		return render(request, 'homepage/search.html', {'query': query, 'sentiment': sentiment, 'mode': mode})
		# except:
		# 	raise
	return render(request, 'homepage/search.html')



