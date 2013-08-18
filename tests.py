'''
Created on 2013-7-1

@author: xuechong
'''
import hashlib
import datetime
from utils import Commons
def sortTests():
    a = "aaaabbc"
    b = "aaabbc"
    c = "aaaaabb"
    list_ = ["bca","abc","aab","aa","ccd"]
    
    print sorted(list_)
    
def sha1test(str):
    sha1 = hashlib.sha1()
if __name__ == "__main__":
    #sortTests()
    #sha1test("aaaaaaaa")
    #help(hashlib.sha1())
    #sha1 = hashlib.sha1()
    #sha1.update("test")
    #print str(sha1.hexdigest())
    tu = (1,2,3)
    list_ = list(tu)
    print list_.pop()
    print list_.pop()
    print tu
    x = datetime.datetime.utcnow() + datetime.timedelta(hours=+8)
    
    
#    print type(x)
#    print help(datetime.datetime)
#    print str(x.year) + str(x.month) + str(x.day)
#    print x.strftime("%Y%m%d")
    print Commons.todayStr()
    
    pass