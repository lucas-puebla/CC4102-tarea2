import matplotlib.pyplot as plt

trees = ["LinearProbingHashingTree", "PatriciaTree", "TernarySearchTree"]
max_length = 10


def plot_action(action):
    if action == "Construction":
        plt.title("Tiempos de construccion")
    elif action == "Similarity":
        plt.title("Tiempos de recorrido")

    maxy = 0

    for tree in trees:
        path = "processed/" + action + tree + ".csv"
        file = open(path, 'r')

        sizes = []
        times = []

        # leer datos
        for line in file.readlines():
            data = line.split(",")
            sizes.append(int(data[0]))

            if action == "Construction":
                times.append(float(data[1]) / 1000000)
                maxy = max(maxy, float(data[1]) / 1000000)
            else:
                times.append(float(data[1]) / 1000)
                maxy = max(maxy, float(data[1]) / 1000)

        # plotear
        plt.plot(sizes, times, label=tree)

    # mostrar
    plt.xlabel('cantidad de palabras')
    plt.ylim((0, maxy * 1.05))
    if action == "Construction":
        plt.ylabel('tiempo (segundos)')
    else:
        plt.ylabel('tiempo (milisegundos)')

    plt.legend()

    plt.savefig('img/' + action + '.png')
    plt.show()
    return


def plot_search(search):
    cm = plt.get_cmap('gist_rainbow')
    for i in range(len(trees)):

        plt.figure(i)
        ax = plt.subplot(111)
        # MUCHOS COLORES (11)
        ax.set_color_cycle([cm(1. * i / 11) for i in range(11)])

        if search == "succ":
            plt.title("Tiempos de busqueda exitosa en " + trees[i])
        elif search == "unsucc":
            plt.title("Tiempos de busqueda infructuosa en " + trees[i])

        path = "processed/" + search + trees[i] + ".csv"
        file = open(path, 'r')

        lengths = []
        times = []
        size = 0

        for line in file.readlines():
            data = line.split(",")

            if len(data) == 1:
                if size != 0:
                    plt.plot(lengths, times, label=str(size))

                size = int(data[0])
                lengths = []
                times = []

            else:
                if data[1] != "None\n":
                    lengths.append(int(data[0]))
                    times.append(float(data[1]))

        plt.plot(lengths, times, label=str(size))

        plt.xlabel('largo de la palabra')
        plt.ylabel('tiempo (microsegundos)')

        box = ax.get_position()
        ax.set_position([box.x0, box.y0, box.width * 0.8, box.height])
        # Put a legend to the right of the current axis
        ax.legend(loc='center left', bbox_to_anchor=(1, 0.5))

        if search == "succ":
            plt.ylim((0, 10))
        else:
            plt.ylim((0, 10))

        plt.savefig('img/' + search + trees[i] + '.png')

    plt.show()
    return


if __name__ == '__main__':
    plot_search("succ")
    plot_search("unsucc")
    plot_action("Construction")
    plot_action("Similarity")
