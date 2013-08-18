# coding: utf-8
'''
Created on 2013-7-7
the coder lao huang li!!! for moegirl wiki
@author: xuechong
'''
from google.appengine.api import memcache
from Weixin import textReply
import logging
from utils.Commons import randomFromList
from utils.Commons import loadFromMemcache
from utils.Commons import todayStr

client = memcache.Client()
__coderCalenderKey__ = ""
__namespace__ = "coderCalender"

todayKey = lambda : "coderCalender" + str(todayStr())

find = lambda: loadFromMemcache(__namespace__, todayKey(), newContent)

class CoderHandler():
    """
    the coder lao huang li!!! for moegirl wiki
    """
    
    @staticmethod
    def __helpkey__ ():
        return __coderCalenderKey__
    
    @staticmethod
    def __helpcontent__():
        return ""
    
    def handle(self,handlerChain):
        if handlerChain.getMsgContent() == __coderCalenderKey__:
            return textReply(handlerChain.userMsg,find())
        else:
            return handlerChain.invokeNext()


_chong = ["Java","Python","C#","Javascript","Perl","C","C++",\
          "Delphi","Objective-c","Basic","PHP","Ruby","Pascal",\
          "Lisp","MATLAB","T-SQL","PL-SQL","GO","Lua","Erlang",\
          "Scala","Groovy","Smalltalk","F#","Fortran","ActionScript",\
          "Bash","Ada"]
_sha = ["Eclipse","Netbeans","Vim","Emacs","UltraEdit","Notepad++",\
        "EditPlus","SublimeText","VisualStudio","Delphi","Aptana",\
        "GCC","DreamWeaver","PowerDesigner","MySql","Oracle","DB2",\
        "MongoDB","SQLServer","Suse","Ubuntu","CentOS","WindowsServer",\
        "Excel","PhotoShop"]

_jy_style_ = ["躺在","跪在","坐在","站在","趴在","倒立在"]
_jy_place_ = ["东南方向","西北方向","西南方向","东北方向","书桌上","电脑桌下","键盘上"\
              ,"马扎上","沙发上","床上","鼠标上","显示器上","平板电脑上"]
_jy_ccontent_ = ["重做系统","部署生产环境","写设计文档","写单元测试","重构代码","需求评审","提交测试"]
_j_detail_ = ["各种装不上驱动会让你发狂","老牛破车一样的网速和版本冲突会让你加班到凌晨4点"]
_y_detail_ = ["今天装系统你能很快找到需要的驱动和应用软件","你的程序架构设计很好,升级不会带来任何问题"]

def newContent():
    logging.info("create new coder content" + todayKey())
    return ""
