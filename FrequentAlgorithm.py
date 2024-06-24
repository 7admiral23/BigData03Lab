def decrement_monitors(monitors):
    keys_to_remove = []
    for key in monitors:
        monitors[key] -= 1
        if monitors[key] == 0:
            keys_to_remove.append(key)
    
    for key in keys_to_remove:
        del monitors[key]

def frequent_algorithm(sequence, k):
    monitor_count = k - 1
    monitors = {}

    for item in sequence:
        if item in monitors:
            monitors[item] += 1
        elif len(monitors) < monitor_count:
            monitors[item] = 1
        else:
            decrement_monitors(monitors)
        print_monitors(monitors)

def print_monitors(monitors):
    print("Monitors:", monitors)

sequence = ['L', 'K', 'M', 'P', 'L', 'K', 'K', 'M', 'P', 'K', 'K', 'P', 'M', 'L', 'P']
k = 4
frequent_algorithm(sequence, k)
