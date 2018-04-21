from django.shortcuts import render
from . import process_search
from django.utils import simplejson



def index(request):
	my_json = [{"coords": [-63.2425206, -32.4079042],"frequency": 9},{"coords": [12.57994249, 55.68087366],"frequency": 3}];
	js_data = simplejson.dumps(my_json)
	return render(request, 'homepage/header.html', {'my_json': js_data});


def home(request):
	if request.method == 'GET':
		query = request.GET['search']
		if query:
			process_search.connect_kafka(query)
			top_tweets_html = process_search.accept_input_for_processing(query)
			sentiment = process_search.get_sentiment()
			return render(request, 'homepage/search.html', {'query': query, 'top_tweets_html': top_tweets_html,
															'sentiment': sentiment})


