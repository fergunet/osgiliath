set terminal postscript color solid
set output "hsv.eps"
set title "Using HSV Histogram Fitness"
set xlabel "Generations"
set ylabel "Fitness"
#plot "gargamel_AVG" title "N1" pt 4 lt -1,"genmagic_AVG" title "N2" pt 6 lt -1,"mercurio_AVG" title "N3" pt 9 lt 9, "melmac_AVG" title "N4" pt 10 lt 10

plot "hsv.csv" u 1:2 title "RGB" with lines lt 0 lc 0, "hsv.csv" u 1:3 title "HSV" with lines lt 2 lc 9, "hsv.csv" u 1:4 title "AVERAGE" with lines lt 3 lc -1
