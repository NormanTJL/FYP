#!/bin/sh
#sort | uniq -u 
if [ -z "$1" ];

then 
	echo "please enter apk location"

else

	
mkdir ~/apk

unzip -q $1 -d ~/apk

dexdump -d ~/apk/classes.dex > ~/apk/classdex.txt

javac featureextractInstructions.java

java featureextractInstructions ~/apk/classes.dex > ~/apk/instructionNgramclassesdex.bin

cp ~/apk/instructionNgramclassesdex.bin instructionNgramclassesdex.bin

gzip ~/apk/classes.dex

tar -zcvf ~/apk/compressednativelib.gz ~/apk/lib/

gzip -c -k -q $1 > ~/apk/zippedapk.gz

xxd -c 5 ~/apk/zippedapk.gz | awk '{print $2,$3,$4}' | tr -d ' '| sed 's/.\{2\}/& /g' > ~/apk/apktemp.txt

xxd -c 5 ~/apk/classes.dex.gz | awk '{print $2,$3,$4}' | tr -d ' '| sed 's/.\{2\}/& /g' > ~/apk/classtemp.txt

xxd -c 5 ~/apk/compressednativelib.gz | awk '{print $2,$3,$4}' | tr -d ' '| sed 's/.\{2\}/& /g' > ~/apk/templibngram.txt

echo "almost there.. "

aapt d permissions $1 AndroidManifest.xml > ~/apk/temp1.txt

javac featureExtract.java

java -Xmx4096m featureExtract ~/apk/apktemp.txt > ~/apk/apkngram.bin

java featureExtract ~/apk/classtemp.txt > ~/apk/classdexngram.bin

java featureExtract ~/apk/templibngram.txt > ~/apk/libngram.bin

cp ~/apk/apkngram.bin apkngram.bin

cp ~/apk/classdexngram.bin classdexngram.bin

cp ~/apk/libngram.bin libngram.bin

rm -rf ~/apk/apktemp.txt
rm -rf ~/apk/classtemp.txt
rm -rf ~/apk/templibngram.txt

javac xmltobitvector.java

java xmltobitvector ~/apk/temp1.txt > ~/apk/usedpermissions.bin

cp ~/apk/usedpermissions.bin usedpermissions.bin

rm -rf ~/apk/temp1.bin

cat ~/apk/*.bin > fingerprint.bin


rm -rf ~/apk/

echo "completed."

fi
