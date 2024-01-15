import functools

def compare(s1,s2):
    
    str = 'abdcefghijklmnopqrstuvwxyz'
    arr1 = []
    arr2 = []
    for i in s1:
        arr1.append( str.index(i.lower()) )
    
    for j in s2:
        arr2.append( str.index(j.lower()) )
        
    ans1 = functools.reduce(lambda x, y: x + y, arr1)
    
    ans2 = functools.reduce(lambda x, y: x + y, arr2)
    
    if( ans1 == ans2 ):
        return True
    else: 
        return False
    
print(compare('AD', 'BC'))