from numpy import median

trees = ["LinearProbingHashingTree", "PatriciaTree", "TernarySearchTree"]
max_length = 8


def init_dict():
    times = dict()

    for i in range(max_length):
        times[i + 1] = []

    return times


def save_dict(times, words, log):
    log.write(str(words) + "\n")

    for i in range(max_length):
        # guardar mediana
        if len(times[i + 1]) > 0:
            med = median(times[i + 1])
        else:
            med = None
        log.write(str(i + 1) + "," + str(med) + "\n")

    return


def process_search(search):
    for tree in trees:
        log = open("processed/" + search + tree + ".csv", 'w')

        path = "results/" + search + "Search" + tree + ".csv"
        file = open(path, 'r')

        size = 0
        times = init_dict()

        for line in file.readlines():
            data = line.split(",")

            # detectar cambios en el tamano
            if size == 0:
                size = int(data[0])
            elif size != int(data[0]):
                save_dict(times, size, log)
                times = init_dict()
                size = int(data[0])

            # agregar al arreglo
            length = int(data[1])
            time = int(data[2])
            if length <= max_length:
                times[length].append(time)

        save_dict(times, size, log)

    return


def process_const(action):
    for tree in trees:
        log = open("processed/" + action + tree + ".csv", 'w')

        path = "results/" + action + tree + ".csv"
        file = open(path, 'r')

        times = dict()

        # acumular tiempos por tamaño
        for line in file.readlines():
            data = line.split(",")

            # agregar al diccionario
            size = int(data[0])
            time = int(data[1])
            if size not in times:
                times[size] = []

            times[size].append(time)

        # guardar mediana de cada tamaño
        for size in times:
            time = times[size]
            med = median(time)

            log.write(str(size) + "," + str(med) + "\n")

    return


if __name__ == '__main__':
    process_search("succ")
    print("succ searchs processed")
    process_search("unsucc")
    print("uncsucc searchs processed")
    process_const("Construction")
    print("constructions processed")
    process_const("Similarity")
    print("similarity processed")
