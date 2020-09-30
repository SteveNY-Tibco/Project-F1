#include <mlpack/core.hpp> 
#include <mlpack/methods/neighbor_search/neighbor_search.hpp> 

using namespace std; 
using namespace mlpack; 
// NeighborSearch and NearestNeighborSort 
using namespace mlpack::neighbor; 
// ManhattanDistance 
using namespace mlpack::metric; 

void mlModel() 
{ 
	// Armadillo is a C++ linear algebra library; 
	// mlpack uses its matrix data type. 
	arma::mat data; 

	/* 
	data::Load is used to import data to the mlpack, 
	It takes 3 parameters, 
		1. Filename = Name of the File to be used 
		2. Matrix = Matrix to hold the Data in the File 
		3. fatal = true if you want it to throw an exception 
		if there is an issue 
	*/
	data::Load("data.csv", data, true); 

	/* 
	Create a NeighborSearch model. The parameters of the 
	model are specified with templates: 
		1. Sorting method: "NearestNeighborSort" - This 
		class sorts by increasing distance. 
		2. Distance metric: "ManhattanDistance" - The 
		L1 distance, the sum of absolute distances. 
		3. Pass the reference dataset (the vectors to 
		be searched through) to the constructor. 
	*/
	NeighborSearch<NearestNeighborSort, ManhattanDistance> nn(data); 
	// in the above line we trained our model or 
	// fitted the data to the model 
	// now we will predict 

	arma::Mat<size_t> neighbors; // Matrices to hold 
	arma::mat distances; // the results 

	/* 
	Find the nearest neighbors. Arguments are:- 
		1. k = 1, Specify the number of neighbors to find 
		2. Matrices to hold the result, in this case, 
		neighbors and distances 
	*/
	nn.Search(1, neighbors, distances); 
	// in the above line we find the nearest neighbor 

	// Print out each neighbor and its distance. 
	for (size_t i = 0; i < neighbors.n_elem; ++i) 
	{ 
		std::cout << "Nearest neighbor of point " << i << " is point "
				<< neighbors[i] << " and the distance is "
				<< distances[i] << ".\n"; 
	} 
} 

int main() 
{ 
	mlModel(); 
	return 0; 
} 
