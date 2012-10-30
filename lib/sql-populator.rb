#!/usr/bin/env ruby

require 'fileutils'

CONFIG_DIR = "/Users/DGB/Dropbox/school/fall-12/databases/assignments/music-store/config/out"

def populate_artists count

  populate_artists_path = File.join( CONFIG_DIR, "populate-artists.sql" )

  # remove old sql query
  FileUtils.rm populate_artists_path

  # generate new generation scripts
  (0..count).each do | unique_index |
    File.open( populate_artists_path, "a" ) do |f|
       f.puts make_artist unique_index
    end
  end
end

# return a customizable string for populating the 'ARTISTS' table
def make_artist unique_index
  artist_name = (0...8).map{ 65.+(rand(26)).chr }.join
  return "INSERT INTO artists VALUES (#{rand(500)}, #{artist_name});"
end

def populate_contacts count

  contact_keys = []

  populate_contact_path = File.join( CONFIG_DIR, "populate-contact.sql" )

  FileUtils.rm populate_contact_path if File.exists? populate_contact_path

  (1..count).each do | unique_index |
    File.open( populate_contact_path, "a" ) do |f|
      f.puts make_contact unique_index
      contact_keys << unique_index
    end
  end
  return contact_keys
end

def make_contact unique_index

  name    = (0...8).map{ 65.+(rand(26)).chr }.join
  address = "#{rand(10000)} #{name}"
  city    = (0...5).map{ 65.+(rand(26)).chr }.join
  state   = (0...2).map{ 65.+(rand(26)).chr }.join
  email   = "#{name}@#{city}.com"
  phone   = "#{rand(999)}-#{rand(999)}-#{rand(9999)}"

  return %{INSERT INTO contact
    VALUES (
      #{unique_index},
      #{name},
      #{address} Street,
      #{city},
      #{state},
      #{rand(90000) + 9999},
      #{email},
      #{phone});
    }
end

def populate_employees contact_keys, count

  populate_contact_path = File.join( CONFIG_DIR, "populate-employees.sql" )

  FileUtils.rm populate_contact_path if File.exists? populate_contact_path

  (1..count).each do | unique_index |
    File.open( populate_contact_path, "a" ) do |f|
      f.puts make_employee unique_index, contact_keys[unique_index]
    end
  end
end

def make_employee unique_index, contact_key

  job_title = (0...8).map{ 65.+(rand(26)).chr }.join
  active    = ['T', 'F'].shuffle.first

  return %{INSERT INTO employee
    VALUES (
      #{unique_index},
      #{job_title},
      #{active},
      #{contact_key}
      );
  }
end

# primary tables
populate_artists 100
contact_keys = populate_contacts 300
populate_employees contact_keys[0..49], 50
# populate_job_titles
# populate_transacton
# populate_customers
# populate_vendors
# populate_music
# populate_promotions

# join tables
# populate_music_transactions
# populate_cutomer_orders
# populate_music_orders
# populate_store_orders
