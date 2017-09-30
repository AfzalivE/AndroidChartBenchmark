# Analysis of Android Chart Benchmarks

# Importing the libraries
import numpy as np
import matplotlib.pyplot as plt
import pandas as pd

# Importing the dataset
dataset = pd.read_csv('chart_benchmark_full.csv')

dataset = dataset * 1e-6
dataset = dataset.diff()
dataset = 1 / (dataset * 1e-3)
dataset = dataset.dropna()

rt_dataset = pd.DataFrame()
rt_dataset['AndroidPlot'] = dataset['ApRtFragment']
rt_dataset['HelloCharts'] = dataset['HcRtFragment']
rt_dataset['MPAndroidCharts'] = dataset['MpRtFragment']
rt_dataset['SciCharts'] = dataset['SciRtFragment']

rt_dataset.plot()

rt3_dataset = pd.DataFrame()
rt3_dataset['AndroidPlot'] = dataset['ApRt3AxesFragment']
rt3_dataset['HelloCharts'] = dataset['HcRt3AxesFragment']
rt3_dataset['MPAndroidCharts'] = dataset['MpRt3AxesFragment']
rt3_dataset['SciCharts'] = dataset['SciRt3AxesFragment']

rt3_dataset.plot()

rtfifo_dataset = pd.DataFrame()
rtfifo_dataset['AndroidPlot'] = dataset['ApRtFifoFragment']
rtfifo_dataset['HelloCharts'] = dataset['HcRtFifoFragment']
rtfifo_dataset['MPAndroidCharts'] = dataset['MpRtFifoFragment']
rtfifo_dataset['SciCharts'] = dataset['SciRtFifoFragment']

rtfifo_dataset.plot()

rtfifo3_dataset = pd.DataFrame()
rtfifo3_dataset['AndroidPlot'] = dataset['ApRtFifo3AxesFragment']
rtfifo3_dataset['HelloCharts'] = dataset['HcRtFifo3AxesFragment']
rtfifo3_dataset['MPAndroidCharts'] = dataset['MpRtFifo3AxesFragment']
rtfifo3_dataset['SciCharts'] = dataset['SciRtFifo3AxesFragment']

rtfifo3_dataset.plot()

dataset.plot()