#!/usr/bin/perl

use strict;
use warnings;
use Net::OpenSSH;

# Get the remote host, SSH key path, and username as command-line arguments
my ($remote_host, $key_path, $username) = @ARGV;

# Check if all required arguments are provided
unless ($remote_host && $key_path && $username) {
    die "Usage: perl remote_script.pl <remote_host> <key_path> <username>";
}

# Create an SSH object
my $ssh = Net::OpenSSH->new($remote_host,
    user => $username,
    key_path => $key_path
);

# Check if the SSH connection was successful
if ($ssh->error) {
    die "SSH connection failed: " . $ssh->error;
}

# Execute the hostname command on the remote host
my ($stdout, $stderr) = $ssh->capture2('hostname');

# Check if the command execution was successful
if ($ssh->error) {
    die "Command execution failed: " . $ssh->error;
}

# Print the output
print "Hostname on $remote_host: $stdout";
