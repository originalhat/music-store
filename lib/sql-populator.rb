#!/usr/bin/env ruby

require 'fileutils'

CONFIG_DIR = "/Users/DGB/Dropbox/school/fall-12/databases/assignments/music-store/config/out"

def populate_artists

  populate_artists_path = File.join( CONFIG_DIR, "populate-artists.sql" )

  # remove old sql query
  FileUtils.rm populate_artists_path

  # generate new generation scripts
  (0..100).each do | unique_index |
    File.open( populate_artists_path, "a" ) do |f|
       f.puts make_artist unique_index
    end
  end
end

# return a customizable string for populating the 'ARTISTS' table
def make_artist unique_index
  artist_name = (0...8).map{ 65.+(rand(26)).chr }.join
  return "INSERT INTO artists (ISBN_NUMBER, ARTIST_NAME) VALUES (#{rand(500)}, #{artist_name});"
end

def populate_contact
  populate_contact_path = File.join( CONFIG_DIR, "populate-contact.sql" )

  FileUtils.rm populate_contact_path if File.exists? populate_contact_path

  (1..300).each do | unique_index |
    File.open( populate_contact_path, "a" ) do |f|
      f.puts make_contact unique_index
    end
  end
end

def make_contact unique_index

  name    = (0...8).map{ 65.+(rand(26)).chr }.join
  address = "#{rand(10000)} #{name}"
  city    = (0...5).map{ 65.+(rand(26)).chr }.join
  state   = (0...2).map{ 65.+(rand(26)).chr }.join
  email   = "#{name}@#{name}.com"
  phone   = "#{rand(999)}-#{rand(999)}-#{rand(9999)}"

  return %{INSERT INTO contact (ID, NAME, ADDRESS, CITY, STATE, ZIPCODE, EMAIL, PHONE)
    VALUES (
      #{unique_index},
      #{name},
      #{address},
      #{city},
      #{state},
      #{rand(10000)},
      #{email},
      #{phone});
    }
end

populate_artists
populate_contact