import sys

allotted_corp = 0
allotted_bore = 0
total_guests = 0
apartment_type = 0

def allot_water(apt_type, ratio):
    global allotted_corp, allotted_bore, apartment_type
    apt_type = int(apt_type)
    corp, bore = map(int, ratio.split(':'))
    total_ratio = corp + bore
    if apt_type ==2:
        allotted = 900
    elif apt_type ==3:
        allotted = 1500
    allotted_corp = round(allotted * corp / total_ratio)
    allotted_bore = round(allotted * bore / total_ratio)
   
    
def add_guests(guests):
    global total_guests
    total_guests += int(guests)

def tanker_cost(tanker):
    cost = 0
    if tanker <=500:
        cost = tanker * 2
    elif tanker <= 1500:
        cost = 500*2 + (tanker - 500)* 3
    elif tanker <=3000:
        cost = 500 * 2 + 1000*3 + (tanker - 1500) *5
    else:
        cost = 500 *2 + 1000 * 3 + 1500* 5 + (tanker -3000) *8
    return cost

def bill():
    allotted_total = allotted_corp + allotted_bore
    guest_water = total_guests * 10 * 30
    total_consumption = allotted_total + guest_water
    
    tanker = max(0, total_consumption - allotted_total)
    
    cost_corp = allotted_corp * 1
    cost_bore = round(allotted_bore * 1.5)
    cost_tanker = tanker_cost(tanker)
    
    total_cost = cost_corp + cost_bore + cost_tanker
    print(total_consumption, total_cost)


def main():
    for line in sys.stdin:
        parts = line.strip().split()
        cmd = parts[0]
        if cmd == 'ALLOT_WATER':
            allot_water(parts[1], parts[2])
        elif cmd == 'ADD_GUESTS':
            add_guests(parts[1])
        elif cmd == 'BILL':
            bill()
            break

if __name__ == '__main__':
    main()
