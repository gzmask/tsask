(defproject tsask "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :plugins [[lein-cljsbuild "0.3.2"] 
            [lein-ring "0.8.5"]]
  :dependencies [[ring/ring "1.1.8"]
                 [ring/ring-jetty-adapter "1.1.8"]
                 [ring-mock "0.1.5"]
                 [ring/ring-json "0.3.1"]
                 [me.raynes/laser "1.1.1"]
                 [hiccup "1.0.3"]
                 [com.draines/postal "1.11.0"]
                 [compojure "1.1.5"]
                 [mysql/mysql-connector-java "5.1.25"]
                 [org.xerial/sqlite-jdbc "3.7.2"]
                 [org.clojure/java.jdbc "0.3.0-alpha4"]
                 [org.clojure/clojure "1.5.1"]
                 [scriptjure "0.1.24"]
                 [enfocus "2.0.0-beta1"]
                 [enlive "1.1.4"]
                 [clj-http "0.7.7"]
                 [me.raynes/laser "0.1.30"]
                 [digest "1.4.3"]
                 [incanter "1.5.4"]]
  :source-paths ["src/clj"]
  :ring {:handler tsask.core/app
         :auto-reload? true
         :adapter {:port 3000}
         :nrepl {:start? true :port 7000}}
  :main tsask.core
  :profiles {:uberjar {:aot :all}})
