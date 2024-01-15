prices = {
    'hot dog': 1.00,
    'burger':  1.50,
    'cheese': 0.50,
    'bread': 1.00
}

def price_loop(item):
    return prices.item

#make sure they are cheking prices
ask = input('Are you looking for prices of items? y/n')

#if they say no nothing else run price loop
if(ask == 'n'):
    None
else:
    thing = input('What item would you like to check out?')
    print(price_loop(thing))