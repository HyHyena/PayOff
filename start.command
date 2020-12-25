path="`dirname \"$0\"`"
cd "$path/front"
npm install
npm run build
cd ..
cd "$path/config"
sudo docker-compose up -d