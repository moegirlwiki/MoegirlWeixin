'''
Created on 2013-8-5

Dictionarys to stroe some init data
@author: xuechong
'''
from google.appengine.ext import db

class Dictionarys(db.Model):
    
    name = db.StringProperty()
    content = db.StringProperty()
    
def findDictByName(dictName):
    query = Dictionarys.all()
    query.filter('name =', dictName)
    return query.get()
