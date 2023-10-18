README

Ants and Doodlebugs- A simulation to demonstrate the effects of predator-prey relationships on population dynamics in a habitat.
Tatum Johnson

About: Ants and doodlebugs live in a habitat, and doodlebugs prey on ants. The habitat is represented by a grid, the ants are represented by black dots, and the doodlebugs are represented by red dots. Users can specify the size of the habitat and the initial populations of ants and doodlebugs. When the user presses enter, time in the simulation advances by one "timestep." Every timestep, the ants and doodlebugs move according to the following rules: 

Rules for ants: Every timestep, each ant randomly moves to an empty adjacent cell in the grid. If the ant survives for three timesteps, it spawns another ant in a randomly selected adjacent cell if there is room.

Rules for doodlebugs: Every timestep, the doodlebug can move to an adjacent cell in the grid as long as another doodlebug doesn't occupy that spot. If the adjacent cell contains an ant, the doodlebug "eats" the ant. If a doodlebug doesn't eat for three timesteps, it "dies." If the doodlebug survives for eight timesteps, it spawns another doodlebug in a random, adjacent empty cell.

After every timestep, the number of doodlebugs and number of ants is displayed in a graph. This allows the user to compare how the populations change over time with different initial habitat sizes and initial population numbers.
