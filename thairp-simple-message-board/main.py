import webapp2
from google.appengine.ext.webapp import template
from google.appengine.dist import use_library
use_library('django', '1.2')

class MainHandler(webapp2.RequestHandler):
    def get(self):
        fakeData = [{'author': 'ME', 'comment' : 'Hello World'},
                    {'author': 'ME 2', 'comment' : 'Hello templates'}]
        data = {'messages' : fakeData}
        self.response.write(template.render('template/main.html', data))
        
app = webapp2.WSGIApplication([
    ('/', MainHandler)
], debug=True)
