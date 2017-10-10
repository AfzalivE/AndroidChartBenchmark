# Analysis of Android Chart Benchmarks

# Importing the libraries
import numpy as np
import matplotlib.pyplot as plt
import pandas as pd

# Importing the dataset
dataset = pd.read_csv('chart_benchmark_nosc.csv')

dataset = dataset * 1e-6
dataset = dataset.diff()
dataset = 1 / (dataset * 1e-3)
dataset = dataset.dropna()

rt_dataset = pd.DataFrame()
rt_dataset['AndroidPlot'] = dataset['ApRtFragment']
rt_dataset['GraphView'] = dataset['GvRtFragment']
rt_dataset['HelloCharts'] = dataset['HcRtFragment']
rt_dataset['MPAndroidCharts'] = dataset['MpRtFragment']
#rt_dataset['SciCharts'] = dataset['ScRtFragment']

rt_dataset.plot(subplots=True, title = "Realtime 1 axis")

rt3_dataset = pd.DataFrame()
rt3_dataset['AndroidPlot'] = dataset['ApRt3AxesFragment']
rt3_dataset['GraphView'] = dataset['GvRt3AxesFragment']
rt3_dataset['HelloCharts'] = dataset['HcRt3AxesFragment']
rt3_dataset['MPAndroidCharts'] = dataset['MpRt3AxesFragment']
#rt3_dataset['SciCharts'] = dataset['ScRt3AxesFragment']

rt3_dataset.plot(subplots=True, title = "Realtime 3 axes")

rtfifo_dataset = pd.DataFrame()
rtfifo_dataset['AndroidPlot'] = dataset['ApRtFifoFragment']
rtfifo_dataset['GraphView'] = dataset['GvRtFifoFragment']
# Remove really high number outlier
rtfifo_dataset['HelloCharts'] = dataset[dataset['HcRtFifoFragment'] < 6000]['HcRtFifoFragment']
rtfifo_dataset['MPAndroidCharts'] = dataset['MpRtFifoFragment']
#rtfifo_dataset['SciCharts'] = dataset['ScRtFifoFragment']

rtfifo_dataset.plot(subplots=True, title = "Realtime FIFO 1 axis")

rtfifo3_dataset = pd.DataFrame()
rtfifo3_dataset['AndroidPlot'] = dataset['ApRtFifo3AxesFragment']
rtfifo3_dataset['GraphView'] = dataset['GvRtFifo3AxesFragment']
rtfifo3_dataset['HelloCharts'] = dataset['HcRtFifo3AxesFragment']
rtfifo3_dataset['MPAndroidCharts'] = dataset['MpRtFifo3AxesFragment']
#rtfifo3_dataset['SciCharts'] = dataset['ScRtFifo3AxesFragment']

rtfifo3_dataset.plot(subplots=True, title = "Realtime FIFO 3 axes")

rt5_dataset = pd.DataFrame()
rt5_dataset['AndroidPlot'] = dataset['Ap5GraphsFifoFragment']
rt5_dataset['GraphView'] = dataset['Gv5GraphsFifoFragment']
rt5_dataset['HelloCharts'] = dataset['Hc5GraphsFifoFragment']
rt5_dataset['MPAndroidCharts'] = dataset['Mp5GraphsFifoFragment']
#rt5_dataset['SciCharts'] = dataset['Sc5GraphsFifoFragment']

rt5_dataset.plot(subplots=True, title = "Realtime FIFO 5 Graphs")

rtap_dataset = pd.DataFrame()
rtap_dataset['OneAxis'] = dataset['ApRtFragment']
rtap_dataset['FifoOneAxis'] = dataset['ApRtFifoFragment']
rtap_dataset['3Axes'] = dataset['ApRt3AxesFragment']
rtap_dataset['Fifo3Axes'] = dataset['ApRtFifo3AxesFragment']
rtap_dataset['5Graphs'] = dataset['Ap5GraphsFifoFragment']

rtap_dataset.plot(subplots=True, title = "AndroidPlot")

rtgv_dataset = pd.DataFrame()
rtgv_dataset['OneAxis'] = dataset['GvRtFragment']
rtgv_dataset['FifoOneAxis'] = dataset['GvRtFifoFragment']
rtgv_dataset['3Axes'] = dataset['GvRt3AxesFragment']
rtgv_dataset['Fifo3Axes'] = dataset['GvRtFifo3AxesFragment']
rtgv_dataset['5Graphs'] = dataset['Gv5GraphsFifoFragment']

rtgv_dataset.plot(subplots=True, title = "GraphView")


rthc_dataset = pd.DataFrame()
rthc_dataset['OneAxis'] = dataset['HcRtFragment']
rthc_dataset['FifoOneAxis'] = dataset['HcRtFifoFragment']
rthc_dataset['3Axes'] = dataset['HcRt3AxesFragment']
rthc_dataset['Fifo3Axes'] = dataset['HcRtFifo3AxesFragment']
rthc_dataset['5Graphs'] = dataset['Hc5GraphsFifoFragment']

rthc_dataset.plot(subplots=True, title = "Hello Charts")

rtmp_dataset = pd.DataFrame()
rtmp_dataset['OneAxis'] = dataset['MpRtFragment']
rtmp_dataset['FifoOneAxis'] = dataset['MpRtFifoFragment']
rtmp_dataset['3Axes'] = dataset['MpRt3AxesFragment']
rtmp_dataset['Fifo3Axes'] = dataset['MpRtFifo3AxesFragment']
rtmp_dataset['5Graphs'] = dataset['Mp5GraphsFifoFragment']

rtmp_dataset.plot(subplots=True, title = "MPAndroidChart")

#rtsc_dataset = pd.DataFrame()
#rtsc_dataset['OneAxis'] = dataset['ScRtFragment']
#rtsc_dataset['FifoOneAxis'] = dataset['ScRtFifoFragment']
#rtsc_dataset['3Axes'] = dataset['ScRt3AxesFragment']
#rtsc_dataset['Fifo3Axes'] = dataset['ScRtFifo3AxesFragment']
#rtsc_dataset['5Graphs'] = dataset['Sc5GraphsFragment']
#
#
#rtsc_dataset.plot(subplots=True)



#dataset.plot(subplots=True)