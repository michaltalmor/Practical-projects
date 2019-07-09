//============================================================================
// Name        : Heroes.cpp
// Author      : 
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
#include <cstdlib>
#include <string>
using namespace std;
#include "Hero.h"
#include "Creature.h"
#include "Game.h"
#include <ctime>

#include <dirent.h>
#include <fstream>
#include <sys/types.h>
#include <sys/stat.h>




int main(int argc, char* argv[]) {

	Game * game;
	string play = "Play";




	if (argc == 5) // new game
	{
		if (argv[1] != play)
		{
			// implement exception
		}

		int numWarriors = atoi(argv[2]);
		int numThiefs = atoi(argv[3]);
		int numNecros = atoi(argv[4]);

		int numHeroes = 0;
		numHeroes = numWarriors + numThiefs + numNecros;

		string heroFolder;
		string heroFile;
		string gameFile;

		if (numWarriors > 3 || numWarriors < 0)
		{
			// implement exception
		}

		if (numThiefs > 3 || numThiefs < 0)
		{
			// implement exception

		}

		if (numNecros > 3 || numNecros < 0)
		{
			// implement exception

		}

		mkdir("game", 0777);
		mkdir("Players", 0777);

		system("rm -r game");
		system("rm -r Players");


		game = new Game(numWarriors, numThiefs, numNecros);

		mkdir("game", 0777);
		ofstream file("game/GameInfo.txt");

		mkdir("Players", 0777);

		for (int i = 0; i < numHeroes; i++) {
			heroFolder = "Players/" + game->getHeroName(i);
			mkdir(heroFolder.c_str(), 0777);
			heroFile = heroFolder + "/info.txt";
			ofstream file(heroFile.c_str());

		}

	}

	else if (argc == 2) // continue game
	{

		string fileTotalHeroes = "";
		string fileCurrentPlayerIndex = "";
		string fileDailyGoldReceived = "";
		string fileSpecialAbilityUsed = "";
		string fileAbleToAttack = "";
		string heroesNamesList = "";
		int totalHeroes;
		int currentPlayerIndex;

		if (argv[1] != play)
		{
			// implement exception
		}

		string line = "";
		DIR* dir = NULL;
		dir = opendir("game");
		if (dir)
		{
			ifstream gameFile("game/GameInfo.txt");


			getline(gameFile, line);

			int i = 0;

			while (line[i] != ',') // total heroes
			{
				fileTotalHeroes = fileTotalHeroes + line[i];
				i++;
			}

			totalHeroes = atoi(fileTotalHeroes.c_str());

			i++;

			while (line[i] != ',') // current index player
			{
				fileCurrentPlayerIndex = fileCurrentPlayerIndex + line[i];
				i++;
			}

			currentPlayerIndex = atoi(fileCurrentPlayerIndex.c_str());


			i++;

			while (line[i] != ',') // daily gold received
			{
				fileDailyGoldReceived = fileDailyGoldReceived + line[i];
				i++;
			}

			i++;

			while (line[i] != ',') // special ability used
			{
				fileSpecialAbilityUsed = fileSpecialAbilityUsed + line[i];
				i++;
			}

			i++;

			int k = line.length();

			while (i < k) // able to attack
			{
				fileAbleToAttack = fileAbleToAttack + line[i];
				i++;
			}


			string heroesNamesList = "";

			getline(gameFile, heroesNamesList);

			game = new Game(totalHeroes, currentPlayerIndex, fileDailyGoldReceived, fileSpecialAbilityUsed, fileAbleToAttack, heroesNamesList);

			closedir(dir);
		}
	}



	game->mainMenu();

	delete game;




}



