'''
Created on 2013-8-5

@author: xuechong
'''
import webapp2
import logging


class Processor(webapp2.RequestHandler):
    
    def get(self):
        write = self.response.out.write
        logging.info("init datas")
        write("init finish")
        
    def post(self):
        self.get()

app = webapp2.WSGIApplication([('/init', Processor)])