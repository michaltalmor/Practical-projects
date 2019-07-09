Ass5.exe: Archer.o Black_Dragon.o Creature.o Game.o Hero.o main.o Necromancer.o Thief.o Vampire.o Warrior.o Wizard.o Zombie.o
	g++ Archer.o Black_Dragon.o Creature.o Game.o Hero.o main.o Necromancer.o Thief.o Vampire.o Warrior.o Wizard.o Zombie.o -o Ass5.exe
Archer.o: Archer.cpp Archer.h Creature.h
	g++ -c Archer.cpp
Black_Dragon.o: Black_Dragon.cpp Black_Dragon.h Creature.h
	g++ -c Black_Dragon.cpp
Creature.o: Creature.cpp Creature.h
	g++ -c Creature.cpp
Game.o: Game.cpp Game.h Hero.h Creature.h Zombie.h Black_Dragon.h \
 Wizard.h Vampire.h Archer.h Necromancer.h Warrior.h Thief.h
	g++ -c Game.cpp
Hero.o: Hero.cpp Hero.h Creature.h Zombie.h Black_Dragon.h Wizard.h \
 Vampire.h Archer.h
	g++ -c Hero.cpp
main.o: main.cpp Hero.h Creature.h Zombie.h Black_Dragon.h Wizard.h \
 Vampire.h Archer.h Game.h Necromancer.h Warrior.h Thief.h
	g++ -c main.cpp
Necromancer.o: Necromancer.cpp Necromancer.h Hero.h Creature.h Zombie.h \
 Black_Dragon.h Wizard.h Vampire.h Archer.h
	g++ -c Necromancer.cpp
Thief.o: Thief.cpp Thief.h Hero.h Creature.h Zombie.h Black_Dragon.h \
 Wizard.h Vampire.h Archer.h
	g++ -c Thief.cpp
Vampire.o: Vampire.cpp Vampire.h Creature.h
	g++ -c Vampire.cpp
Warrior.o: Warrior.cpp Warrior.h Hero.h Creature.h Zombie.h \
 Black_Dragon.h Wizard.h Vampire.h Archer.h
	g++ -c Warrior.cpp
Wizard.o: Wizard.cpp Wizard.h Creature.h
	g++ -c Wizard.cpp
Zombie.o: Zombie.cpp Zombie.h Creature.h
	g++ -c Zombie.cpp
clean:
	rm -f *.o

