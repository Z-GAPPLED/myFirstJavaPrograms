import tkinter as tk
from tkinter import Menu, Text, Entry, filedialog, messagebox

class pythonGUI:
    def __init__(self):
        self.count = 0

        self.root = tk.Tk()
        self.root.title("My first GUI")

        self.button_increment = tk.Button(self.root, text="Increment Count", command=self.increment_count)
        self.button_increment.pack(pady=10)

        self.button_open_gui2 = tk.Button(self.root, text="Fullscreen editor", command=self.open_test_gui2)
        self.button_open_gui2.pack(pady=10)

        self.button_reset_increment = tk.Button(self.root, text="Reset the Increment", command=self.reset_increment)
        self.button_reset_increment.pack(pady=10)

        self.label = tk.Label(self.root, text="Number of clicks: 0")
        self.label.pack()

        # Creating the MenuBar and adding components
        self.menu_bar = Menu(self.root)
        file_menu = Menu(self.menu_bar, tearoff=0)
        self.menu_bar.add_cascade(label="FILE", menu=file_menu)
        self.menu_bar.add_command(label="Help", command=self.show_help)
        file_menu.add_command(label="Open", command=self.open_file)
        file_menu.add_command(label="Save as", command=self.save_file)

        # Creating the panel at the bottom and adding components
        self.panel = tk.Frame(self.root)
        self.label_file_path = tk.Label(self.panel, text="File Path: Not opened")
        self.label_file_path.grid(row=0, column=0, columnspan=5)

        self.label_input = tk.Label(self.panel, text="Enter Text")
        self.tf = Entry(self.panel, width=10)
        self.send = tk.Button(self.panel, text="Send", command=lambda: self.send_clicked(self.tf))
        self.reset = tk.Button(self.panel, text="Reset", command=lambda: self.reset_clicked(self.tf))
        self.go_back = tk.Button(self.panel, text="Exit", command=self.go_back_clicked)

        self.label_input.grid(row=1, column=0)
        self.tf.grid(row=1, column=1)
        self.send.grid(row=1, column=2)
        self.reset.grid(row=1, column=3)
        self.go_back.grid(row=1, column=4)

        # Text Area at the Center
        self.ta = Text(self.root)

        # Adding Components to the frame
        self.panel.pack(side=tk.BOTTOM)
        self.ta.pack(expand=True, fill=tk.BOTH)

        self.root.config(menu=self.menu_bar)
        self.root.protocol("WM_DELETE_WINDOW", self.on_close)

        # File path variable
        self.file_path = None

    def increment_count(self):
        self.count += 1
        self.label.config(text=f"Number of clicks: {self.count}")

    def open_test_gui2(self):
        # Open an instance of PythonGUI2
        pythonGUI2(self.root)

    def reset_increment(self):
        self.count = 0
        self.label.config(text=f"Number of clicks: {self.count}")

    def show_help(self):
        messagebox.showinfo("Help", "This is a simple GUI application.")

    def open_file(self):
        file_path = filedialog.askopenfilename(defaultextension=".txt", filetypes=[("Text Files", "*.txt"), ("All Files", "*.*")])
        if file_path:
            self.file_path = file_path
            self.label_file_path.config(text=f"File Path: {self.file_path}")
            with open(file_path, "r") as file:
                content = file.read()
                self.ta.delete(1.0, tk.END)
                self.ta.insert(tk.END, content)

    def save_file(self):
        if self.file_path:
            content = self.ta.get(1.0, tk.END)
            with open(self.file_path, "w") as file:
                file.write(content)
        else:
            self.save_file_as()

    def save_file_as(self):
        file_path = filedialog.asksaveasfilename(defaultextension=".txt", filetypes=[("Text Files", "*.txt"), ("All Files", "*.*")])
        if file_path:
            self.file_path = file_path
            self.label_file_path.config(text=f"File Path: {self.file_path}")
            content = self.ta.get(1.0, tk.END)
            with open(file_path, "w") as file:
                file.write(content)

    def send_clicked(self, tf):
        if not tf.get().strip():
            # If there is nothing typed, do nothing
            return
        text = tf.get() + "\n"
        tf.delete(0, tk.END)
        self.ta.insert(tk.END, text)

    def reset_clicked(self, tf):
        tf.delete(0, tk.END)
        self.ta.delete(1.0, tk.END)

    def go_back_clicked(self):
        self.root.destroy()

    def on_close(self):
        self.root.destroy()

    def run(self):
        self.root.mainloop()

class pythonGUI2:
    def __init__(self, root):
        self.root = tk.Toplevel(root)
        self.root.title("PythonGUI2")

        # Creating the MenuBar and adding components
        menu_bar = Menu(self.root)
        file_menu = Menu(menu_bar, tearoff=0)
        menu_bar.add_cascade(label="FILE", menu=file_menu)
        menu_bar.add_command(label="Help", command=self.show_help)
        file_menu.add_command(label="Open", command=self.open_file)
        file_menu.add_command(label="Save as", command=self.save_file)

        # Creating the panel at the bottom and adding components
        panel = tk.Frame(self.root)
        label = tk.Label(panel, text="Enter Text")
        tf = Entry(panel, width=10)
        send = tk.Button(panel, text="Send", command=lambda: self.send_clicked(tf))
        reset = tk.Button(panel, text="Reset", command=lambda: self.reset_clicked(tf))
        go_back = tk.Button(panel, text="Return", command=self.go_back_clicked)

        label.grid(row=0, column=0)
        tf.grid(row=0, column=1)
        send.grid(row=0, column=2)
        reset.grid(row=0, column=3)
        go_back.grid(row=0, column=4)

        # Text Area at the Center
        self.ta = Text(self.root)

        # Adding Components to the frame.
        panel.pack(side=tk.BOTTOM)
        self.ta.pack(expand=True, fill=tk.BOTH)

        self.root.config(menu=menu_bar)
        self.root.protocol("WM_DELETE_WINDOW", self.on_close)

        # File path variable
        self.file_path = None

    def show_help(self):
        messagebox.showinfo("Help", "This is PythonGUI2.")

    def open_file(self):
        file_path = filedialog.askopenfilename(defaultextension=".txt", filetypes=[("Text Files", "*.txt"), ("All Files", "*.*")])
        if file_path:
            self.file_path = file_path
            with open(file_path, "r") as file:
                content = file.read()
                self.ta.delete(1.0, tk.END)
                self.ta.insert(tk.END, content)

    def save_file(self):
        if self.file_path:
            content = self.ta.get(1.0, tk.END)
            with open(self.file_path, "w") as file:
                file.write(content)
        else:
            self.save_file_as()

    def save_file_as(self):
        file_path = filedialog.asksaveasfilename(defaultextension=".txt", filetypes=[("Text Files", "*.txt"), ("All Files", "*.*")])
        if file_path:
            self.file_path = file_path
            content = self.ta.get(1.0, tk.END)
            with open(file_path, "w") as file:
                file.write(content)

    def send_clicked(self, tf):
        if not tf.get().strip():
            # If there is nothing typed, do nothing
            return
        text = tf.get() + "\n"
        tf.delete(0, tk.END)
        self.ta.insert(tk.END, text)

    def reset_clicked(self, tf):
        tf.delete(0, tk.END)
        self.ta.delete(1.0, tk.END)

    def go_back_clicked(self):
        self.root.destroy()

    def on_close(self):
        self.root.destroy()

# Run the PythonGUI instance
pythonGUI().run()
