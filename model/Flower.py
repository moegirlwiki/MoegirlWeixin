# coding: utf-8
'''
Created on 2013-7-4

@author: xuechong
'''

import logging
from google.appengine.api import search

#class Flower(search.SearchableModel):
#    
#    name = db.StringProperty()
#    content = db.StringProperty()
#    
#    def description(self):
#        result = self.name + unicode(":\n","utf-8")\
#             + self.content + unicode("\n","utf-8")
#        return result
#    @classmethod
#    def SearchableProperties(cls):
#        return [['name'],search.ALL_PROPERTIES]
#    
#    
#def save2(name,content):
#    flower = Flower()
#    flower._key_name = name
#    flower.content = content
#    flower.name = name
#    flower.put()
    
#def findByName2(searchStr):
#    logging.debug("search flower" + searchStr)
#    query = Flower.all()
#    return query.search(searchStr.decode("utf-8"), properties=['name']).fetch(20)
#    #query = db.GqlQuery("SELECT * FROM Flower WHERE name >= :1 AND name < :2", searchStr.decode("utf-8"), searchStr.decode("utf-8") + u"\ufffd")
#    #return query.fetch(20)
class Flower():
    name = ""
    content = ""
    
    def description(self):
        result = self.name + unicode(":\n","utf-8")\
             + self.content + unicode("\n","utf-8")
        return result
    
_indexName = "flowers"

def save(name,content):
    try:
        document = search.Document(
        fields=[search.TextField(name='name', value=name),
                search.TextField(name='content', value=content)
                ],
        language='zh')
        search.Index(name=_indexName).put(document)
    except Exception as e:
        logging.error(e)
        
def findByName(searchStr):
    searchResults = search.Index(_indexName).search("name:"+searchStr.decode("utf-8"))
    if searchResults:
        result = list()
        for flowerDoc in searchResults:
            flower = Flower()
            flower.name = flowerDoc.fields[0].value
            flower.content = flowerDoc.fields[1].value
            result.append(flower)
        return result
    return []

def removeall():
    doc_index = search.Index(name=_indexName)
    while True:
        document_ids = [document.doc_id
                        for document in doc_index.get_range(ids_only=True)]
        if not document_ids:
            break
        doc_index.delete(document_ids)
