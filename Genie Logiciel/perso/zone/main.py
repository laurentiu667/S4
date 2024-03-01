import tkinter as tk
import random
import math
import time

class Perlin_noise:
    def __init__(self):
        self.size = 200         # Map detail (+grand = +relief)
        self.intensity = 170    # Frequence des cours d'eau (-grand = +d'eau)
        self.smooth = 7         # Smooth les edges
        self.array = []

    def generate_random(self):
        x = random.random()
        return math.trunc(x * self.intensity)
    def generate_noise(self):
        for i in range(self.size):
            row = []
            for j in range(self.size):
                row.append(self.generate_random())
            self.array.append(row)
    def smoothing(self):
        for k in range(self.smooth):
            for i in range(self.size - 1):
                for j in range(self.size - 1):
                    total = 0
                    total += self.array[i - 1][j - 1]
                    total += self.array[i - 1][j]
                    total += self.array[i - 1][j + 1]
                    total += self.array[i][j - 1]
                    total += self.array[i][j + 1]
                    total += self.array[i + 1][j - 1]
                    total += self.array[i + 1][j]
                    total += self.array[i + 1][j + 1]

                    self.array[i][j] = (total/8)

class Vue():
    def __init__(self):
        self.root = tk.Tk()
        self.width = 800
        self.canvas = tk.Canvas(self.root, width=self.width, height=self.width)

    def generate_square(self, size, array):
        square_size = self.width/size
        x1 = 0
        y1 = 0
        x2 = square_size
        y2 = square_size
        for i in range(size - 1):
            row = array[i]
            for j in range(size - 1):
                if 0 < row[j] < 10:
                    color = "gray1"
                elif 10 < row[j] < 20:
                    color = "gray10"
                elif 20 < row[j] < 30:
                    color = "gray20"
                elif 30 < row[j] < 40:
                    color = "black"
                elif 40 < row[j] < 50:
                    color = "navy"
                elif 50 < row[j] < 60:
                    color = "navy"
                elif 60 < row[j] < 70:
                    color = "royalBlue"
                elif 70 < row[j] < 80:
                    color = "dodgerBlue"
                elif 80 < row[j] < 90:
                    color = "forestGreen"
                elif 90 < row[j]:
                    color = "darkGreen"

                self.canvas.create_rectangle(x1,y1,x2,y2, fill=color, outline="")
                x1 += square_size
                x2 += square_size
                if math.trunc(x1) == math.trunc((size * square_size) - square_size) or math.trunc(x1) == (math.trunc((size * square_size)- square_size)) - 1:
                    y1 += square_size
                    y2 += square_size
                    x2 = square_size
                    x1 = 0

        self.canvas.pack()

class Seed():
    def __init__(self):
        self.time = math.trunc(time.time())
        self.biomeOrder = []
        self.seed = self.get_seed()
        self.biomeDict = {1: "ForetFeuillu",
                          2: "ForetFeuillu",
                          3: "ForetBoreale",
                          4: "ForetBoreale",
                          5: "Prairies",
                          6: "Prairies",
                          7: "Marais",
                          8: "Marais",
                          9: "Toundra",
                          0: "Toundra"}

    def get_seed(self):
        random.seed(self.time)
        seed = 0
        while(len(str(math.trunc(seed)))) != 10:
            seed = random.random()
            seed = seed * 10000000000
        return math.trunc(seed)

    def set_biomes(self):
        for i in range(1, 10, 1):
            x = int(str(self.seed)[i])
            self.biomeOrder.append(self.biomeDict[x])

seed = Seed()
seed.set_biomes()
print(seed.biomeOrder)

perlin = Perlin_noise()
perlin.generate_noise()
perlin.smoothing()

vue = Vue()
vue.generate_square(perlin.size, perlin.array)
vue.root.mainloop()
