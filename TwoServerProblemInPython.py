def get_city_index(city):
    cities = {'L': 0, 'H': 1, 'B': 2, 'I': 3, 'A': 4}
    return cities[city]

# Distances between the cities
distances = [
    [0, 400, 300, 1900, 2600],
    [400, 0, 500, 2400, 2900],
    [300, 500, 0, 2300, 2700],
    [1900, 2400, 2300, 0, 800],
    [2600, 2900, 2700, 800, 0]
]

# Initial server positions
server1 = get_city_index('B')
server2 = get_city_index('L')

sequence = "HALALI"
total_cost = 0

for city in sequence:
    city_index = get_city_index(city)
    cost1 = distances[server1][city_index]
    cost2 = distances[server2][city_index]

    if cost1 <= cost2:
        total_cost += cost1
        server1 = city_index
    else:
        total_cost += cost2
        server2 = city_index

print("Total cost:", total_cost)
