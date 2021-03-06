# connections

Visualizing connections between politicians and organizations.
Project officially started after I proposed it to [Datademo](http://datademo.fi) program.

I try to make this as usable as possible for the public so everybody could
use it to check how the politicians are connected. The source data comes
from Parliament of Finland (stored separately).

Datademo program ends in few months but I plan to continue developing this after that.

## Contributing

The project is still in early phase so there isn't much to do at this very
moment for non-developers. I still have to implement the basic
functionalities and set up the hosting to accept help from the public.
After that there are plenty of work in data entry and verification.

If you want to help, please contact me.

If you have experience in developing with Clojure, ClojureScript or
D3.js and want to participate in development (esp. with cljs and D3),
please contact me.

## Features

* Basic text search for entries. Wildcard search works with syntax <code>Espoo.+</code>
* Basic connection list views for entries
* Basic path views for connections. This means you can search how two entries are connected behind the surface

## Todo List

* Edit functionalities
* Graphic presentation of connections with D3.js
* UI work

## Usage and Technologies Used

First you have to have a [Neo4j](http://www.neo4j.org) server running somewhere.
Then you have to change the server configuration to match your setup (<code>src/clj/connections/neo4j.clj</code>).
Connection to Neo4j server is implemented with [Neocons](http://clojureneo4j.info) library.

Project uses [ClojureScript](https://github.com/clojure/clojurescript) and Facebook React to render the views.
To compile the source to javascript use <code>lein cljsbuild once</code> or <code>lein cljsbuild auto</code> (for continuous development).

To start the included [ring](https://github.com/ring-clojure/ring) web server use <code>lein ring server</code>.

User interface is done with [Om](https://github.com/swannodette/om) which is a ClojureScript interface to Facebook's [React](https://facebook.github.io/react/).

UI work is in heavy development mode as I am currently learning Om and React.

## License

Copyright © 2014 Teemu Frisk

Distributed under the Eclipse Public License.
