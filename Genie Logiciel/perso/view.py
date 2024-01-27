from tkinter import *
from datetime import datetime

class View():
    def __init__(self, controller):
        self.controller = controller
        self.background_cl = "#161A30"
        self.background_cl2 = "#31304D"
        self.background_cl3 = "#B6BBC4"
        self.background_cl4 = "#F0ECE5"
        self.window = Tk()
        self.window.title("Title Name")
        self.window.config(bg=self.background_cl)
        self.window.geometry('800x600')

        self.draw()
        self.update_time()  # changent l heure

    def draw(self):
        # creation du frame heure minutes secondes
        container = Frame(self.window)
        container.pack(fill='x')

        # cration interieure
        hour = Label(container, background=self.background_cl2, foreground="black", width=10, height=3, text="Hour",
                     font=('Helvetica', 15, 'bold'))
        minute = Label(container, background=self.background_cl2, foreground="black", width=10, text="Minute",
                       font=('Helvetica', 15, 'bold'))
        second = Label(container, background=self.background_cl2, foreground="black", width=10, text="Second",
                       font=('Helvetica', 15, 'bold'))

        # utilisation de grid
        hour.grid(row=0, column=0, sticky="nsew")
        minute.grid(row=0, column=1, sticky="nsew")
        second.grid(row=0, column=2, sticky="nsew")

        # centrer les grid
        container.grid_rowconfigure(0, weight=1)
        container.grid_columnconfigure(0, weight=1)
        container.grid_columnconfigure(1, weight=1)
        container.grid_columnconfigure(2, weight=1)

        # creationd de la 2 eme frame pour afficher l heure
        container2 = Frame(self.window, width=10, height=140)
        container2.pack(fill='x')

        # initaliser a 0
        hourVar = Label(container2, background=self.background_cl, foreground="black", width=10, height=5,
                        font=('Helvetica', 15, 'bold'), text="0")
        minuteVar = Label(container2, background=self.background_cl, foreground="black", width=10,
                          font=('Helvetica', 15, 'bold'), text="0")
        secondVar = Label(container2, background=self.background_cl, foreground="black", width=10,
                          font=('Helvetica', 15, 'bold'), text="0")

        hourVar.grid(row=0, column=0, sticky="nsew")
        minuteVar.grid(row=0, column=1, sticky="nsew")
        secondVar.grid(row=0, column=2, sticky="nsew")

        # meme chose que l autre
        container2.grid_columnconfigure(0, weight=1)
        container2.grid_columnconfigure(1, weight=1)
        container2.grid_columnconfigure(2, weight=1)
        container2.grid_rowconfigure(0, weight=1)

        # avoir acces plustard
        self.hourVar = hourVar
        self.minuteVar = minuteVar
        self.secondVar = secondVar

    def update_time(self):
        current_time = datetime.now()
        hours = current_time.hour
        minutes = current_time.minute
        seconds = current_time.second

        # Update the time labels
        self.hourVar.config(text=str(hours))
        self.minuteVar.config(text=str(minutes))
        self.secondVar.config(text=str(seconds))

        # Schedule the update_time function to be called after 1000 milliseconds (1 second)
        self.window.after(1000, self.update_time)

    def main(self):
        self.window.mainloop()