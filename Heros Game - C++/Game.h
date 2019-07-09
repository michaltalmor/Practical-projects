/*
 * Game.h
 *
 *  Created on: Jan 3, 2019
 *      Author: ise
 */

#ifndef GAME_H_
#define GAME_H_

#include <stdio.h>



#include <cstdlib>
#include <ctime>

#include <string>
#include <iostream>
using namespace std;
#include "Hero.h"
#include "Necromancer.h"
#include "Warrior.h"
#include "Thief.h"



#include <dirent.h>
#include <fstream>
#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <time.h>
#include <sstream>

class Game {

private:
	Hero** HeroesList;
	int totalHeroes;
	int heroTurnIndex;
	bool* playingHeroesArray;
	bool checkLegalName(string name);
	bool isAbleToAttack;
	bool isDailyGoldReceived;
	bool isSpecialAbilityUsed;



public:
	Game(int totalHeroes, int currentPlayerIndex, string dailyGoldUsed, string specialAbilityUsed, string ableToAttack, string heroesNamesList);
	Game(int w, int t, int n);
	Game();
	~Game();
	Hero& getHeroByName(string name);
	void mainMenu();
	void AttackMenu();
	int getHeroIndex(Hero& other);
	void BuyCreaturesMenu();
	string getHeroName(int i);


};

#endif /* GAME_H_ */
