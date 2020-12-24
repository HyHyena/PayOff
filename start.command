path="`dirname \"$0\"`"
cd "$path/front"
npm run build
cd ..
cd "$path/config"
docker-compose up