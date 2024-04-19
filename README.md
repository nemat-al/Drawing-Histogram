# Drawing Histograms
An academic project at HIAST aimed to draw histogram out of data file. The project is written in Java programing language.

## Index
1. [Output Example](#output-example)
2. [Input data file example](#input-data-file-example)
3. [Options in GUI](#options-in-gui)

---

## Output Example
In the following picture we can see a screenshot of the GUI of the program. While the input is a file with data about precipitation rate, the output is a histogram representing the data.

![alt text](https://github.com/nemat-al/Drawing-Histogram/blob/main/imgs/GUI_example.png " GUI example")


## Input data file example
In order to run this program, the data file format should be as shown in the following image:

![alt text](https://github.com/nemat-al/Drawing-Histogram/blob/main/imgs/File_Format.png " File Format")

In order to explain the format of the data file, we will take an example of data file about precipitation rate for cities in Syria.
The first line should  contain the title. Then, for each city there is the name of it and then a set of lines, in each line there is the year and the precipitation rate.

## Options in GUI
The user can enters a specific option in the TextField, and the program verifies the validity of the entered option. Then according to that, a convenient histogram is drawn, given that the available options are:
- (All): The program draws a histogram of average rainfall over the years for each of the existing governorates in the entered file.
- (A digit) If this number is smaller than the number of governorates in the file, then the program will draw an average precipitation for the corresponding governorate by its rank in the data file. The plot will be in terms of the corresponding years.
- More than one digit separated by commas (For example “1,2,3”). After checking the presence of the correspondings governorates by ranks, the program draws 
an average rainfall histogram for the corresponding governorates according to their ranks.


---

Note: The project with developed within a team of two.
