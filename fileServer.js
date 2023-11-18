/*slip 6: 
Create a Node.js file that opens the requested file and returns the content to the client. 
If anything goes wrong, throw a 404 error
 */
const http = require('http');
const fs = require('fs');
const path = require('path');

const server = http.createServer((req, res) => {
  // Extract the requested file path from the URL
  const filePath = path.join(__dirname, 'files', req.url);

  // Read the file asynchronously
  fs.readFile(filePath, 'utf8', (err, data) => {
    if (err) {
      if (err.code === 'ENOENT') {
        // File not found (404 error)
        res.writeHead(404, {'Content-Type': 'text/plain'});
        res.end('404 Not Found');
      } else {
        // Other error
        res.writeHead(500, {'Content-Type': 'text/plain'});
        res.end('Internal Server Error');
      }
    } else {
      // File found, send the content
      res.writeHead(200, {'Content-Type': 'text/plain'});
      res.end(data);
    }
  });
});

const port = 3000;

server.listen(port, () => {
  console.log(`Server is running at http://localhost:${port}`);
});