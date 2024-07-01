import random
from collections import Counter

class BIT:
    def __init__(self, n, init_val=0):
        self.size = n
        self.tree = [init_val] * (n + 1)

    def sum(self, i):
        s = 0
        while i > 0:
            s += self.tree[i]
            i -= i & -i
        return s

    def update(self, i, delta):
        while i <= self.size:
            self.tree[i] += delta
            i += i & -i

def read_and_compute_frequencies(filename):
    with open(filename, 'r') as file:
        data = file.read().replace('\n', '')
    frequencies = Counter(data)
    total = sum(frequencies.values())
    probabilities = {char: freq / total for char, freq in frequencies.items()}
    return probabilities, data

def static_list_cost(probabilities, alpha_order=True):
    if alpha_order:
        sorted_probs = sorted(probabilities.items())  # Alphabetical order
    else:
        sorted_probs = sorted(probabilities.items(), key=lambda x: x[1], reverse=True)  # Probability order
    cn = sum((i + 1) * prob for i, (char, prob) in enumerate(sorted_probs))
    return cn

def mtf_cost(probabilities):
    chars = list(probabilities.keys())
    n = len(chars)
    predicted_cost = 1 + sum(sum(probabilities[ci] * probabilities[cj] / (probabilities[ci] + probabilities[cj]) for j, cj in enumerate(chars) if i != j) for i, ci in enumerate(chars))
    return predicted_cost

def mtf_encode_decode(data):
    symbol_table = sorted(set(data))
    output = []
    for symbol in data:
        index = symbol_table.index(symbol)
        output.append(index)
        symbol_table.pop(index)
        symbol_table.insert(0, symbol)
    observed_cost = sum(output[i] + 1 for i in range(len(output))) / len(output)
    return observed_cost

def simulate_bit(data, char_to_index, init_val=0):
    bit = BIT(256, init_val)
    total_cost = 0
    for char in data:
        index = char_to_index[char]
        total_cost += bit.sum(index)
        bit.update(index, 1)
    return total_cost / len(data)

def process_file(filename):
    probabilities, data = read_and_compute_frequencies(filename)
    char_to_index = {char: i + 1 for i, char in enumerate(sorted(set(data)))}

    alpha_cost = static_list_cost(probabilities, alpha_order=True)
    proba_cost = static_list_cost(probabilities, alpha_order=False)
    predicted_mtf_cost = mtf_cost(probabilities)
    observed_mtf_cost = mtf_encode_decode(data)
    observed_bit_random_cost = simulate_bit(data, char_to_index, init_val=random.randint(0, 1))
    observed_bit_deterministic_cost = simulate_bit(data, char_to_index, init_val=0)
    delta_random = 100 * (observed_bit_random_cost - observed_mtf_cost) / observed_mtf_cost
    delta_deterministic = 100 * (observed_bit_deterministic_cost - observed_mtf_cost) / observed_mtf_cost

    print(f"File: {filename}")
    print(f"Static list cost (alpha) Predicted: {alpha_cost}, Observed: {alpha_cost}")
    print(f"Static list cost (proba) Predicted: {proba_cost}, Observed: {proba_cost}")
    print(f"Predicted MTF Cost: {predicted_mtf_cost}")
    print(f"Observed MTF Cost: {observed_mtf_cost}")
    # print(f"Observed BIT Random cost is: {observed_bit_random_cost}")
    # print(f"Observed BIT Deterministic cost is: {observed_bit_deterministic_cost}")
    print(f"Delta (BIT Random): {delta_random}%")
    print(f"Delta (BIT Deterministic): {delta_deterministic}%")

# Example usage for each file
process_file('Requests_Toy.txt')
process_file('Requests_TM.txt')
process_file('Requests_Exp.txt')