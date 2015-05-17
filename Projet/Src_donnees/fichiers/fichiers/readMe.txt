Comment affecter les sujets

Utilisation de glpsol (glpk)

glpsol -m nomDuModele.mod -d nomDesDonnees.txt [-o fichier de sortie en format lisible à l'oeil] [-w fichier de sortie en format "traitable"]

Exemples : 
glpsol -m ipipip-modele-min-nb-sujets.mod -d ipipip-data-a1.txt -o sol2-print.txt -w sol2-plain.txt

glpsol -m ipipip-modele-binomes.mod -d ipipip-data-a1.txt -o sol2-print.txt -w sol2-plain.txt


le bon fichier excel est 
ipipip-solution-binomes-2015.xls

recopier tel quel le fichier sol-plain.txt dans l'onglet solution-brute, les affectation sont automatiquement mises à jour dans les deux onglets "Liste élèves" et "Affectation"

