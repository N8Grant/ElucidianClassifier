# ElucidianClassifier

Data Overview

This program is a cimple classifier which is used to determine a houses proximity to water. The 5 classes are
[NEAR BAY, <1H OCEAN, INLAND, NEAR OCEAN, ISLAND]. 

Classification

In order to classify a given data point, the Elucidian distance to each property is taken with respect to all of the points 
in the training set. Since the classes of the training set are known the resultant distance is summed up in a vector for each of the \
classes and is then averaged out depending on how many data points belonged to each class.
