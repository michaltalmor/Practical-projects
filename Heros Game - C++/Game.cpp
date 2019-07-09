/*
 * Game.cpp
 *
 *  Created on: Jan 3, 2019
 *      Author: ise
 */

#include "Game.h"




Game::Game(int totalHeroes, int currentPlayerIndex, string dailyGoldUsed, string specialAbilityUsed, string ableToAttack, string heroesNamesList) {

	this->totalHeroes = totalHeroes;
	HeroesList = new Hero*[totalHeroes];
	heroTurnIndex = currentPlayerIndex;
	playingHeroesArray = new bool [totalHeroes];
	for (int i = 0; i < totalHeroes; i++)
		playingHeroesArray[i] = true;


	double goldAmount;
	int zombies;
	int vampires;
	int archers;
	int wizards;
	int blackDragons;
	string heroName = "";
	string type = "";
	string goldAmountFile = "";
	string zombiesFile = "";
	string vampiresFile = "";
	string archersFile = "";
	string wizardsFile = "";
	string blackDragonsFile = "";
	isAbleToAttack = false;
	isDailyGoldReceived = false;
	isSpecialAbilityUsed = false;
	DIR* dir = NULL;




	if (dailyGoldUsed == "true")
		isDailyGoldReceived = true;


	if (specialAbilityUsed == "true")
		isSpecialAbilityUsed = true;



	if (ableToAttack == "true")
		isAbleToAttack = true;


	string dirName = "";
	string fileName= "";
	int i = 0;
	int heroCounter = 0;

	int k = 0;

	for (int j = 0; j < totalHeroes; j++) {

			while (heroesNamesList[k] != ',') {
				heroName = heroName + heroesNamesList[k];
				k++;
			}

			k++;


				dirName = "Players/" + heroName;
				dir = opendir(dirName.c_str());

				if (dir)
				{

					string line = "";
					fileName = dirName + "/info.txt";
					ifstream heroFile(fileName.c_str());
					getline(heroFile, line);

					heroName = "";

					while (line[i] != ',') // hero name
					{
						heroName = heroName + line[i];
						i++;
					}

					i++;

					while (line[i] != ',') // hero type
					{
						type = type + line[i];
						i++;
					}

					i++;


					while (line[i] != ',') // gold amount
					{
						goldAmountFile = goldAmountFile + line[i];
						i++;
					}

					i++;

					goldAmount = atoi(goldAmountFile.c_str());

					while (line[i] != ',') // zombies
					{
						zombiesFile = zombiesFile + line[i];
						i++;
					}

					i++;

					zombies = atoi(zombiesFile.c_str());

					while (line[i] != ',') // vampires
					{
						vampiresFile = vampiresFile + line[i];
						i++;
					}

					i++;

					vampires = atoi(vampiresFile.c_str());

					while (line[i] != ',') // archers
					{
						archersFile = archersFile + line[i];
						i++;
					}

					i++;

					archers = atoi(archersFile.c_str());

					while (line[i] != ',') // wizards
					{
						wizardsFile = wizardsFile + line[i];
						i++;
					}

					i++;

					wizards = atoi(wizardsFile.c_str());

					int len = line.length();

					while (i < len) // black dragons
					{
						blackDragonsFile = blackDragonsFile + line[i];
						i++;
					}

					blackDragons = atoi(blackDragonsFile.c_str());


					closedir(dir);

					line = "";
				}

				if (type == "Warrior")
					HeroesList[heroCounter] = new Warrior(heroName);

				else if (type == "Thief")
					HeroesList[heroCounter] = new Thief(heroName);

				else if (type == "Necromancer")
					HeroesList[heroCounter] = new Necromancer(heroName);

				HeroesList[heroCounter]->setGoldAmount(goldAmount);
				HeroesList[heroCounter]->getCreaturesList(0)->setCount(zombies);
				HeroesList[heroCounter]->getCreaturesList(1)->setCount(vampires);
				HeroesList[heroCounter]->getCreaturesList(2)->setCount(archers);
				HeroesList[heroCounter]->getCreaturesList(3)->setCount(wizards);
				HeroesList[heroCounter]->getCreaturesList(4)->setCount(blackDragons);

				heroCounter++;

				heroName = "";
				type = "";
				goldAmountFile = "";
				zombiesFile = "";
				vampiresFile = "";
				archersFile = "";
				wizardsFile = "";
				blackDragonsFile = "";

				i = 0;

			}
}






Game::Game(int w, int t, int n) {

	// implement if w,t,n > 3, close program

	totalHeroes = w + t + n;
	HeroesList = new Hero*[totalHeroes];
	heroTurnIndex = 0;
	playingHeroesArray = new bool [totalHeroes];

	for (int i = 0; i < totalHeroes; i++)
		playingHeroesArray[i] = false;

	srand(time(NULL));
	int num = rand() % totalHeroes;

	int i;

	for (i = 0; i < w; i++) {
		while (playingHeroesArray[num] == true) {
			num = rand() % totalHeroes;
		}
		string warName;

	isAbleToAttack = false;
	isDailyGoldReceived = false;
	isSpecialAbilityUsed = false;

	bool isValid=false;
	while (isValid==false){

		try{

			cout<< "Please insert warrior number " << i+1 << " name:" << endl;

			getline(cin, warName);

			if (checkLegalName(warName) == true)
			{
				HeroesList[num] = new Warrior(warName);
				playingHeroesArray[num] = true;
				isValid=true;
			}
			else
			{
			throw exception();

			}
		}
		catch(exception &e){
			cout << "Please insert legal name"<< endl;
		}
	}
	}

	for (i = 0; i < t; i++) {
		while (playingHeroesArray[num] == true) {
			num = rand() % totalHeroes;
		}

		string thiefName;

		bool isValid=false;
		while (isValid==false){

				try{

				cout<< "Please insert thief number " << i+1 << " name:" << endl;

				getline(cin, thiefName);

					if (checkLegalName(thiefName) == true)
					{
						HeroesList[num] = new Thief(thiefName);
						playingHeroesArray[num] = true;
						isValid=true;
					}
					else
					{
						throw exception();

				}
				}
				catch (exception &e){
					cout << "Please insert legal name"<< endl;
				}
		}
	}

	for (i = 0; i < n; i++) {
		while (playingHeroesArray[num] == true) {
			num = rand() % totalHeroes;
		}

		string necroName;

		bool isValid=false;
		while (isValid==false){
		try{


		cout<< "Please insert necromancer number " << i+1 << " name:" << endl;
		getline(cin, necroName);


		if (checkLegalName(necroName) == true)
		{
			HeroesList[num] = new Necromancer(necroName);
			playingHeroesArray[num] = true;
			isValid=true;
		}
		else
		{
			throw exception();

		}
		}
		catch (exception &e){

			cout << "Please insert legal name"<< endl;
		}
		}
	}

}

Game::~Game() {
	// TODO Auto-generated destructor stub
}

bool Game::checkLegalName(string name) {
	int k = name.length();

	if (k > 30)
	{

		// implement exception

	}

	for (int i = 0; i < k; i++)
		if (!(name[i] >= 'a' && name[i] <= 'z') && !(name[i] >= 'A' && name[i] <= 'Z') && !(name[i] >= '0' && name[i] <= '9'))
			return false;

	return true;
}

string Game::getHeroName(int i) {
	if (i >= 0 && i <= totalHeroes-1)
		return HeroesList[i]->getName();

	return "";
}

Hero& Game::getHeroByName(string name) {

	for (int i = 0; i < totalHeroes; i++)
		if (HeroesList[i]->getName() == name)
			return *(HeroesList[i]);

	// implement Exception
	// delete the warrior returned by this function

	throw exception();

	//return *(new Warrior(""));///// just for compile

}

int Game::getHeroIndex(Hero& other) {

	for (int i = 0; i < totalHeroes; i++)
		if (HeroesList[i]->getName() == other.getName())
			return i;

	// implement exceptions what happens when hero no longer exists (died)?

	return -1;
}

void Game::mainMenu() {

	int turnsCounter = 0;

	bool isvalid =false;


	string indexCurrPlayer = "";
	string dailyGoldReceived = "false";
	string specialAbilityUsed = "false";
	string ableToAttack = "false";
	string heroPath = "";
	string heroNameToFile = "";
	string heroTypeToFile = "";
	string goldToFile = "";
	string zombiesToFile = "";
	string vampiresToFile = "";
	string archersToFile = "";
	string wizardsToFile = "";
	string blackDragonsToFile = "";
	string writeHeroToFile = "";
	string heroFilePath = "";
	string totalHeroesToFile = "";
	int totalHeroesFile = 0;

	int choice = 0;

	while (choice != 7)
	{



		cout << "Welcome " << HeroesList[heroTurnIndex]->getName() << endl;

		cout << "What is your next step in the path to victory?" << endl;

		cout << "1. Attack" << endl;
		cout << "2. Get daily gold" << endl;
		cout << "3. Buy creatures" << endl;
		cout << "4. Show details" << endl;
		cout << "5. Special skill" << endl;
		cout << "6. End of my turn" << endl;
		cout << "7. Exit" << endl;

		cin >> choice;

		int aliveHeroesCounter;
		int aliveHero;

	switch(choice) {

	case 1: // Attack


			AttackMenu();

			aliveHeroesCounter=0;
			for (int k=0 ; k< totalHeroes ; k++){
				if (playingHeroesArray[k]==true){
					aliveHeroesCounter++;
					aliveHero=k;
				}
			}
			if (aliveHeroesCounter==1) {
				cout << HeroesList[aliveHero]->getName() << " is the winner!"<<endl;
				choice = 7;

				//remove the game
				for (int i=0; i<totalHeroes; i++){

							delete HeroesList[i];
					}
					delete [] HeroesList;
					delete [] playingHeroesArray;
			}

		else
		{
			// implement exception
			// check if throw exception in main menu or attack menu
			// make sure to write roundsNumber to file for continuing games
		}




		break;


	case 2: // get daily gold


		if (isDailyGoldReceived == false) {
			HeroesList[heroTurnIndex]->getDailyGold();
			isDailyGoldReceived = true;
		}


		// implement when game closes and opens after daily gold received, the user cannot get money again


		break;


	case 3: // buy creatures

		BuyCreaturesMenu();

		break;



	case 4: // show details

		HeroesList[heroTurnIndex]->printDetails();

		break;

	case 5: // ‫‪Special‬‬ ‫‪skill‬‬

		if (isSpecialAbilityUsed == false) {
			if (HeroesList[heroTurnIndex]->getType()=="Warrior") {

				HeroesList[heroTurnIndex]->specialAbility();
				cout<< "Gold added successfully"<<endl;
				isSpecialAbilityUsed = true;
			}
			else if (HeroesList[heroTurnIndex]->getType()=="Thief") {

				isvalid =false;
				string other;

				cout<<"Please insert hero name:"<<endl;
						while (isvalid==false){

							cin>>other;

							try{
								getHeroByName(other);
								isvalid=true;
							}
							catch(exception &e){
								cout << "Please insert valid name" << endl;
							}

						}
						HeroesList[heroTurnIndex]->specialAbility(getHeroByName(other));
						isSpecialAbilityUsed = true;


			}
			else if (HeroesList[heroTurnIndex]->getType()=="Necromancer") {
				HeroesList[heroTurnIndex]->specialAbility();
				cout<<"Zombie added successfully"<<endl;
				isSpecialAbilityUsed = true;

			}
		}

		// implement when game closes and opens after special ability used, the user cannot use special ability again


		break;

	case 6: //‫‪End‬‬ ‫‪of‬‬ ‫‪my‬‬ ‫‪turn:

			turnsCounter++;

		if (turnsCounter >= totalHeroes*3)
			isAbleToAttack = true;

		if (heroTurnIndex==totalHeroes-1){
					heroTurnIndex=0;
			}
			else{
				heroTurnIndex++;
			}

		while (playingHeroesArray[heroTurnIndex]==false){

			if (heroTurnIndex==totalHeroes-1){
				heroTurnIndex=0;
			}
			else{
				heroTurnIndex++;
			}

		}

		isDailyGoldReceived = false;
		isSpecialAbilityUsed = false;




		break;
	case 7: // exit


		DIR* dir;
		DIR* heroDir;
		dir = opendir("game");
		int indexCurrentPlayer;

		for (int j = 0; j < totalHeroes; j++)
			if (playingHeroesArray[j] == true)
				totalHeroesFile++;

		if (dir)
		{
			ofstream gameFile("game/GameInfo.txt");

			 indexCurrentPlayer = heroTurnIndex;

			if (isDailyGoldReceived == true)
				dailyGoldReceived = "true";

			if (isSpecialAbilityUsed == true)
				specialAbilityUsed = "true";

			if (isAbleToAttack == true)
				ableToAttack = "true";

			stringstream convertIndex, convertTotalHeroes;
			convertIndex << indexCurrentPlayer;
			convertTotalHeroes << totalHeroesFile;
			indexCurrPlayer = convertIndex.str();
			totalHeroesToFile = convertTotalHeroes.str();


			string writeGameToFile = totalHeroesToFile + "," + indexCurrPlayer + "," + dailyGoldReceived + "," + specialAbilityUsed + "," + ableToAttack;

			gameFile << writeGameToFile;
			gameFile << "\n";

			string heroesNamesList = "";

			for(int i = 0; i < totalHeroes; i++)
				if (playingHeroesArray[i] == true)
					heroesNamesList = heroesNamesList + getHeroName(i) + ",";

			gameFile << heroesNamesList;

			closedir(dir);
		}

		for (int i = 0; i < totalHeroes; i++) {
			if (playingHeroesArray[i] == true) {

				heroPath = "Players/" + getHeroName(i);
				heroDir = opendir(heroPath.c_str());

				if (heroDir)
				{

					heroFilePath = heroPath+"/info.txt";

					ofstream heroFile(heroFilePath.c_str());

					heroNameToFile = getHeroName(i);
					heroTypeToFile = (getHeroByName(heroNameToFile)).getType();

					stringstream convertGold, convertZombies, convertVampires, convertArchers, convertWizards, convertBlackDragons;
					convertGold << (getHeroByName(heroNameToFile)).getGoldAmount();
					convertZombies << (getHeroByName(heroNameToFile)).getCreaturesList(0)->getCount();
					convertVampires << (getHeroByName(heroNameToFile)).getCreaturesList(1)->getCount();
					convertArchers << (getHeroByName(heroNameToFile)).getCreaturesList(2)->getCount();
					convertWizards << (getHeroByName(heroNameToFile)).getCreaturesList(3)->getCount();
					convertBlackDragons << (getHeroByName(heroNameToFile)).getCreaturesList(4)->getCount();

					goldToFile = convertGold.str();
					zombiesToFile = convertZombies.str();
					vampiresToFile = convertVampires.str();
					archersToFile = convertArchers.str();
					wizardsToFile = convertWizards.str();
					blackDragonsToFile = convertBlackDragons.str();


					writeHeroToFile = heroNameToFile + "," + heroTypeToFile + "," + goldToFile + "," + zombiesToFile + "," + vampiresToFile + "," + archersToFile + "," + wizardsToFile + "," + blackDragonsToFile;
					heroFile << writeHeroToFile;

					closedir(heroDir);
				}

			}
			else
			{
				heroPath = "Players/" + getHeroName(i);
				string deletePath = "rm -r "+heroPath;
				mkdir(heroPath.c_str(), 0777);
				system(deletePath.c_str());
			}
		}





		// implement

	// need to save the detals fo the game

		for (int i=0; i<totalHeroes; i++){

				delete HeroesList[i];
		}
		delete [] HeroesList;
		delete [] playingHeroesArray;



		break;

	default:


		// implement


		break;
		}

	}
}

void Game::BuyCreaturesMenu() {

	int choice;

	cout << "1. Buy Zombies." << endl;
	cout << "2. Buy Archers." << endl;
	cout << "3. Buy Vampire." << endl;
	cout << "4. Buy Wizard." << endl;
	cout << "5. Buy Black Dragon." << endl;

	cin >> choice;

	int creaturesNum;

	switch(choice) {

		case 1: // zombies

			cout << "Attack level: 2, Defense level: 5"<< endl;
			cout << "Please enter how many Zombies you want to buy: " << endl;
			cin >> creaturesNum;
			HeroesList[heroTurnIndex]->buyCreatures("Zombie", creaturesNum);
			break;

		case 2: // archers

			cout << "Attack level: 5, Defense level: 4"<< endl;
			cout << "Please enter how many Archers you want to buy: " << endl;
			cin >> creaturesNum;
			HeroesList[heroTurnIndex]->buyCreatures("Archer", creaturesNum);

			break;

		case 3: // vampires

			cout << "Attack level: 4, Defense level: 4"<< endl;
			cout << "Please enter how many Vampires you want to buy: " << endl;
			cin >> creaturesNum;
			HeroesList[heroTurnIndex]->buyCreatures("Vampire", creaturesNum);

			break;

		case 4: // wizards

			cout << "Attack level: 8, Defense level: 2"<< endl;
			cout << "Please enter how many Wizards you want to buy: " << endl;
			cin >> creaturesNum;
			HeroesList[heroTurnIndex]->buyCreatures("Wizard", creaturesNum);

			break;

		case 5: // black dragons

			cout << "Attack level: 9, Defense level: 10"<< endl;
			cout << "Please enter how many Black Dragons you want to buy: " << endl;
			cin >> creaturesNum;
			HeroesList[heroTurnIndex]->buyCreatures("Black_Dragon", creaturesNum);

			break;

		default:


			// implement


			break;

	}


}
void Game::AttackMenu(){


	int choice = 0;
	string opponentName;

	cout << "1. Show me my opponents" << endl;
	cout << "2. Attack hero" << endl;
	cin >> choice;
	bool gameContinue = true;

	bool isValid = false;

	switch(choice) {

	case 1: // Show me my opponents
		for (int i = 0; i < totalHeroes; i++) {

			if (HeroesList[i]->getType() == "Warrior" && HeroesList[i]->getName() != HeroesList[heroTurnIndex]->getName())
				cout << HeroesList[i]->getName() << " Warrior" << endl;
		}

		for (int i = 0; i < totalHeroes; i++) {
			if (HeroesList[i]->getType() == "Thief" && HeroesList[i]->getName() != HeroesList[heroTurnIndex]->getName())
				cout << HeroesList[i]->getName() << " Thief" << endl;
		}

		for (int i = 0; i < totalHeroes; i++) {
			if (HeroesList[i]->getType() == "Necromancer" && HeroesList[i]->getName() != HeroesList[heroTurnIndex]->getName())
				cout << HeroesList[i]->getName() << " Necromancer" << endl;
		}

		fgetc(stdin);
		std::cin.ignore();


		break;


	case 2: // Attack hero

		if (isAbleToAttack == true) {
		isValid =false;
		while (isValid==false){
			cout << "Please insert your opponent name:" << endl;
			cin >> opponentName;

			try{
				getHeroByName(opponentName);
				isValid=true;
			}
			catch(exception &e){
				cout << "Please insert valid name" << endl;
			}
		}







		while (gameContinue == true) {

			cout << HeroesList[heroTurnIndex]->getName() << " " << HeroesList[heroTurnIndex]->getType() << ":" << endl;

			if (HeroesList[heroTurnIndex]->getCreaturesList(4)->getCount() > 0){
				cout << HeroesList[heroTurnIndex]->getCreaturesList(4)->getCount() << " Black_Dragon";
			if (HeroesList[heroTurnIndex]->getCreaturesList(3)->getCount() > 0 || HeroesList[heroTurnIndex]->getCreaturesList(2)->getCount() > 0 || HeroesList[heroTurnIndex]->getCreaturesList(1)->getCount() > 0 || HeroesList[heroTurnIndex]->getCreaturesList(0)->getCount() > 0)
				cout << " ";
			}

			if (HeroesList[heroTurnIndex]->getCreaturesList(3)->getCount() > 0){
				cout << HeroesList[heroTurnIndex]->getCreaturesList(3)->getCount() << " Wizard";
			if (HeroesList[heroTurnIndex]->getCreaturesList(2)->getCount() > 0 || HeroesList[heroTurnIndex]->getCreaturesList(1)->getCount() > 0 || HeroesList[heroTurnIndex]->getCreaturesList(0)->getCount() > 0)
				cout << " ";
			}

			if (HeroesList[heroTurnIndex]->getCreaturesList(2)->getCount() > 0){
				cout << HeroesList[heroTurnIndex]->getCreaturesList(2)->getCount() << " Archer";
			if (HeroesList[heroTurnIndex]->getCreaturesList(1)->getCount() > 0 || HeroesList[heroTurnIndex]->getCreaturesList(0)->getCount() > 0)
				cout << " ";
			}

			if (HeroesList[heroTurnIndex]->getCreaturesList(1)->getCount() > 0){
				cout << HeroesList[heroTurnIndex]->getCreaturesList(1)->getCount() << " Vampire";
			if (HeroesList[heroTurnIndex]->getCreaturesList(0)->getCount() > 0)
				cout << " ";
			}

			if (HeroesList[heroTurnIndex]->getCreaturesList(0)->getCount() > 0)
				cout << HeroesList[heroTurnIndex]->getCreaturesList(0)->getCount() << " Zombie";

			cout << ".\n" << endl;

			cout << getHeroByName(opponentName).getName() << " " << getHeroByName(opponentName).getType() << ":" << endl;

			if (getHeroByName(opponentName).getCreaturesList(4)->getCount() > 0){
				cout << getHeroByName(opponentName).getCreaturesList(4)->getCount() << " Black_Dragon";
			if (getHeroByName(opponentName).getCreaturesList(3)->getCount() > 0 || getHeroByName(opponentName).getCreaturesList(2)->getCount() > 0 || getHeroByName(opponentName).getCreaturesList(1)->getCount() > 0 || getHeroByName(opponentName).getCreaturesList(0)->getCount() > 0)
				cout << " ";
			}

			if (getHeroByName(opponentName).getCreaturesList(3)->getCount() > 0){
				cout << getHeroByName(opponentName).getCreaturesList(3)->getCount() << " Wizard";
			if (getHeroByName(opponentName).getCreaturesList(2)->getCount() > 0 || getHeroByName(opponentName).getCreaturesList(1)->getCount() > 0 || getHeroByName(opponentName).getCreaturesList(0)->getCount() > 0)
				cout << " ";
			}

			if (getHeroByName(opponentName).getCreaturesList(2)->getCount() > 0){
				cout << getHeroByName(opponentName).getCreaturesList(2)->getCount() << " Archer";
			if (getHeroByName(opponentName).getCreaturesList(1)->getCount() > 0 || getHeroByName(opponentName).getCreaturesList(0)->getCount() > 0)
				cout << " ";
			}

			if (getHeroByName(opponentName).getCreaturesList(1)->getCount() > 0){
				cout << getHeroByName(opponentName).getCreaturesList(1)->getCount() << " Vampire";
			if (getHeroByName(opponentName).getCreaturesList(0)->getCount() > 0)
				cout << " ";
			}

			if (getHeroByName(opponentName).getCreaturesList(0)->getCount() > 0)
				cout << getHeroByName(opponentName).getCreaturesList(0)->getCount() << " Zombie";

			cout << "." << endl;

			cout << HeroesList[heroTurnIndex]->getName() << "'s turn:" << endl;


			string myCreature;
			string opCreature;



			isValid=false;
			while (isValid==false){
				cin >> myCreature;
				cin >> opCreature;
				try {
					if (myCreature != "Zombie" && myCreature != "Archer" && myCreature != "Vampire" && myCreature != "Wizard" && myCreature != "Black_Dragon")
					{
						throw exception();
					}
					for (int j=0 ; j<5 ; j++){
						if ((HeroesList[heroTurnIndex]->getCreaturesList(j)->getName())==myCreature && HeroesList[heroTurnIndex]->getCreaturesList(j)->getCount()==0){
							throw exception();
						}

					}

					if (opCreature != "Zombie" && opCreature != "Archer" && opCreature != "Vampire" && opCreature != "Wizard" && opCreature != "Black_Dragon")
					{
						throw exception();
					}
					for (int j=0 ; j<5 ; j++){
						if (getHeroByName(opponentName).getCreaturesList(j)->getName()==opCreature && getHeroByName(opponentName).getCreaturesList(j)->getCount()==0){

							throw exception();
						}

					}
					isValid=true;
				}
				catch (exception &e){
					cout << "please insert legal creatur names" << endl;
				}
			}


			HeroesList[heroTurnIndex]->attack(getHeroByName(opponentName), myCreature, opCreature);


			if (getHeroByName(opponentName).getTotalCreatures() == 0) { // Hero died
				playingHeroesArray[getHeroIndex(getHeroByName(opponentName))] = false;
				gameContinue = false;



				cout << "You have been victorious"<< endl;
			}

			else
			{

				cout << getHeroByName(opponentName).getName() << " " << getHeroByName(opponentName).getType() << ":" << endl;


				if (getHeroByName(opponentName).getCreaturesList(4)->getCount() > 0){
					cout << getHeroByName(opponentName).getCreaturesList(4)->getCount() << " Black_Dragon";
				if (getHeroByName(opponentName).getCreaturesList(3)->getCount() > 0 || getHeroByName(opponentName).getCreaturesList(2)->getCount() > 0 || getHeroByName(opponentName).getCreaturesList(1)->getCount() > 0 || getHeroByName(opponentName).getCreaturesList(0)->getCount() > 0)
					cout << " ";
				}

				if (getHeroByName(opponentName).getCreaturesList(3)->getCount() > 0){
					cout << getHeroByName(opponentName).getCreaturesList(3)->getCount() << " Wizard";
				if (getHeroByName(opponentName).getCreaturesList(2)->getCount() > 0 || getHeroByName(opponentName).getCreaturesList(1)->getCount() > 0 || getHeroByName(opponentName).getCreaturesList(0)->getCount() > 0)
					cout << " ";
				}

				if (getHeroByName(opponentName).getCreaturesList(2)->getCount() > 0){
					cout << getHeroByName(opponentName).getCreaturesList(2)->getCount() << " Archer";
				if (getHeroByName(opponentName).getCreaturesList(1)->getCount() > 0 || getHeroByName(opponentName).getCreaturesList(0)->getCount() > 0)
					cout << " ";
				}

				if (getHeroByName(opponentName).getCreaturesList(1)->getCount() > 0){
					cout << getHeroByName(opponentName).getCreaturesList(1)->getCount() << " Vampire";
				if (getHeroByName(opponentName).getCreaturesList(0)->getCount() > 0)
					cout << " ";
				}

				if (getHeroByName(opponentName).getCreaturesList(0)->getCount() > 0)
					cout << getHeroByName(opponentName).getCreaturesList(0)->getCount() << " Zombie";

				cout << ".\n" << endl;

				cout << HeroesList[heroTurnIndex]->getName() << " " << HeroesList[heroTurnIndex]->getType() << ":" << endl;

				if (HeroesList[heroTurnIndex]->getCreaturesList(4)->getCount() > 0){
					cout << HeroesList[heroTurnIndex]->getCreaturesList(4)->getCount() << " Black_Dragon";
				if (HeroesList[heroTurnIndex]->getCreaturesList(3)->getCount() > 0 || HeroesList[heroTurnIndex]->getCreaturesList(2)->getCount() > 0 || HeroesList[heroTurnIndex]->getCreaturesList(1)->getCount() > 0 || HeroesList[heroTurnIndex]->getCreaturesList(0)->getCount() > 0)
					cout << " ";
				}

				if (HeroesList[heroTurnIndex]->getCreaturesList(3)->getCount() > 0){
					cout << HeroesList[heroTurnIndex]->getCreaturesList(3)->getCount() << " Wizard";
				if (HeroesList[heroTurnIndex]->getCreaturesList(2)->getCount() > 0 || HeroesList[heroTurnIndex]->getCreaturesList(1)->getCount() > 0 || HeroesList[heroTurnIndex]->getCreaturesList(0)->getCount() > 0)
					cout << " ";
				}

				if (HeroesList[heroTurnIndex]->getCreaturesList(2)->getCount() > 0){
					cout << HeroesList[heroTurnIndex]->getCreaturesList(2)->getCount() << " Archer";
				if (HeroesList[heroTurnIndex]->getCreaturesList(1)->getCount() > 0 || HeroesList[heroTurnIndex]->getCreaturesList(0)->getCount() > 0)
					cout << " ";
				}

				if (HeroesList[heroTurnIndex]->getCreaturesList(1)->getCount() > 0){
					cout << HeroesList[heroTurnIndex]->getCreaturesList(1)->getCount() << " Vampire";
				if (HeroesList[heroTurnIndex]->getCreaturesList(0)->getCount() > 0)
					cout << " ";
				}

				if (HeroesList[heroTurnIndex]->getCreaturesList(0)->getCount() > 0)
					cout << HeroesList[heroTurnIndex]->getCreaturesList(0)->getCount() << " Zombie";

				cout << "." << endl;

				cout << getHeroByName(opponentName).getName() << "'s turn:" << endl;

				isValid=false;
				while (isValid == false){


					cin >> opCreature;
					cin >> myCreature;

					try {
						if (myCreature != "Zombie" && myCreature != "Archer" && myCreature != "Vampire" && myCreature != "Wizard" && myCreature != "Black_Dragon")
						{
							throw exception();

						}
						for (int j=0 ; j<5 ; j++){
							if ((HeroesList[heroTurnIndex]->getCreaturesList(j)->getName())==myCreature && HeroesList[heroTurnIndex]->getCreaturesList(j)->getCount()==0){
								throw exception();
							}

						}


						if (opCreature != "Zombie" && opCreature != "Archer" && opCreature != "Vampire" && opCreature != "Wizard" && opCreature != "Black_Dragon")
						{
							throw exception();
						}
						for (int j=0 ; j<5 ; j++){
							if (getHeroByName(opponentName).getCreaturesList(j)->getName()==opCreature && getHeroByName(opponentName).getCreaturesList(j)->getCount()==0){

								throw exception();
							}

						}
						isValid=true;

					}
					catch (exception &e){
						cout << "please insert legal creatur names" << endl;
					}
				}
				getHeroByName(opponentName).attack(*(HeroesList[heroTurnIndex]), opCreature,myCreature);


				if (HeroesList[heroTurnIndex]->getTotalCreatures() == 0) { // Hero died
					playingHeroesArray[heroTurnIndex] = false;
					gameContinue = false;

					int k=0;
					while (k<totalHeroes && playingHeroesArray[heroTurnIndex]==false){

						if (heroTurnIndex==totalHeroes-1){
							heroTurnIndex=0;
						}
						else{
							heroTurnIndex++;
						}
					k++;
					}

					cout << "You have been perished"<< endl;

				}
			}
		}
	}
			else
				cout << "please play 3 rounds" << endl;
		break;

	default:

		// implement

		break;




	}
}



