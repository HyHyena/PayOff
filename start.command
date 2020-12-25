path="`dirname \"$0\"`"
cd "$path/front"
npm i
npm run build
cd ..
cd "$path/config"
sudo docker-compose up -d