# ElucidianClassifier

Data Overview

This program is a cimple classifier which is used to determine a houses proximity to water. The 5 classes are
[NEAR BAY, <1H OCEAN, INLAND, NEAR OCEAN, ISLAND]. There are 9 other house attributes which are all in the form of doubles. These include:
1. longitude: A measure of how far west a house is; a higher value is farther west
2. latitude: A measure of how far north a house is; a higher value is farther north
3. housingMedianAge: Median age of a house within a block; a lower number is a newer building
4. totalRooms: Total number of rooms within a block
5. totalBedrooms: Total number of bedrooms within a block
6. population: Total number of people residing within a block
7. households: Total number of households, a group of people residing within a home unit, for a block
8. medianIncome: Median income for households within a block of houses (measured in tens of thousands of US Dollars)
9. medianHouseValue: Median house value for households within a block (measured in US Dollars)
10. oceanProximity: Location of the house w.r.t ocean/sea

Classification

In order to classify a given data point, the Elucidian distance to each property is taken with respect to all of the points 
in the training set. Since the classes of the training set are known the resultant distance is summed up in a vector for each of the classes and is then averaged out depending on how many data points belonged to each class. The lowst average is then used as the models prediction.

Results

Running the classifier 30 times yields an average classification accuracy of about 45%. Given a random selection probability of 20% this seems like a significant increase in accuracy, but there is still some improvement that can be made on this model.
