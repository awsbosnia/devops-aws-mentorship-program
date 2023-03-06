#!/bin/bash
# provjera vrijednosti varijable nakon for petlje

for test in Mostar, Sarajevo, "Banja Luka"
do
    echo "Grad je $test"
done
echo "Zadnji grad u for petlji je"
echo $test
echo "Promjena vrijednosti varijable test"
test=Mostar
echo "Vrijednost je promjenjena na: $test"
