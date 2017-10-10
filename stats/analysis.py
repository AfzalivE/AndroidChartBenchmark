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
#rt_dataset['GraphView'] = dataset['GvRtFragment']
rt_dataset['HelloCharts'] = dataset['HcRtFragment']
rt_dataset['MPAndroidCharts'] = dataset['MpRtFragment']
rt_dataset['SciCharts'] = dataset['SciRtFragment']

rt_dataset.plot(subplots=True)

rt3_dataset = pd.DataFrame()
rt3_dataset['AndroidPlot'] = dataset['ApRt3AxesFragment']
#rt3_dataset['GraphView'] = dataset['GvRt3AxesFragment']
rt3_dataset['HelloCharts'] = dataset['HcRt3AxesFragment']
rt3_dataset['MPAndroidCharts'] = dataset['MpRt3AxesFragment']
rt3_dataset['SciCharts'] = dataset['SciRt3AxesFragment']

rt3_dataset.plot(subplots=True)

rtfifo_dataset = pd.DataFrame()
rtfifo_dataset['AndroidPlot'] = dataset['ApRtFifoFragment']
#rtfifo_dataset['GraphView'] = dataset['GvRtFifoFragment']
# Remove really high number outlier
rtfifo_dataset['HelloCharts'] = dataset[dataset['HcRtFifoFragment'] < 6000]['HcRtFifoFragment']
rtfifo_dataset['MPAndroidCharts'] = dataset['MpRtFifoFragment']
rtfifo_dataset['SciCharts'] = dataset['SciRtFifoFragment']

rtfifo_dataset.plot(subplots=True)

rtfifo3_dataset = pd.DataFrame()
rtfifo3_dataset['AndroidPlot'] = dataset['ApRtFifo3AxesFragment']
#rtfifo3_dataset['GraphView'] = dataset['GvRtFifo3AxesFragment']
rtfifo3_dataset['HelloCharts'] = dataset['HcRtFifo3AxesFragment']
rtfifo3_dataset['MPAndroidCharts'] = dataset['MpRtFifo3AxesFragment']
rtfifo3_dataset['SciCharts'] = dataset['SciRtFifo3AxesFragment']

rtfifo3_dataset.plot(subplots=True)

rtap_dataset = pd.DataFrame()
rtap_dataset['OneAxis'] = dataset['ApRtFragment']
rtap_dataset['FifoOneAxis'] = dataset['ApRtFifoFragment']
rtap_dataset['3Axes'] = dataset['ApRt3AxesFragment']
rtap_dataset['Fifo3Axes'] = dataset['ApRtFifo3AxesFragment']

rtap_dataset.plot(subplots=True)

#rtgv_dataset = pd.DataFrame()
#rtgv_dataset['OneAxis'] = dataset['GvRtFragment']
#rtgv_dataset['FifoOneAxis'] = dataset['GvRtFifoFragment']
#rtgv_dataset['3Axes'] = dataset['GvRt3AxesFragment']
#rtgv_dataset['Fifo3Axes'] = dataset['GvRtFifo3AxesFragment']
#
#rtgv_dataset.plot(subplots=True)


rthc_dataset = pd.DataFrame()
rthc_dataset['OneAxis'] = dataset['HcRtFragment']
rthc_dataset['FifoOneAxis'] = dataset['HcRtFifoFragment']
rthc_dataset['3Axes'] = dataset['HcRt3AxesFragment']
rthc_dataset['Fifo3Axes'] = dataset['HcRtFifo3AxesFragment']

rthc_dataset.plot(subplots=True)

rtmp_dataset = pd.DataFrame()
rtmp_dataset['OneAxis'] = dataset['MpRtFragment']
rtmp_dataset['FifoOneAxis'] = dataset['MpRtFifoFragment']
rtmp_dataset['3Axes'] = dataset['MpRt3AxesFragment']
rtmp_dataset['Fifo3Axes'] = dataset['MpRtFifo3AxesFragment']

rtmp_dataset.plot(subplots=True)

rtsc_dataset = pd.DataFrame()
rtsc_dataset['OneAxis'] = dataset['SciRtFragment']
rtsc_dataset['FifoOneAxis'] = dataset['SciRtFifoFragment']
rtsc_dataset['3Axes'] = dataset['SciRt3AxesFragment']
rtsc_dataset['Fifo3Axes'] = dataset['SciRtFifo3AxesFragment']

rtsc_dataset.plot(subplots=True)



#dataset.plot(subplots=True)